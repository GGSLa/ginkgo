package club.uglyland.service;

import club.uglyland.application.FileOperationPath;
import club.uglyland.application.ResponseCode;
import club.uglyland.dao.FileNodeDAO;
import club.uglyland.dao.PanAuthorityDAO;
import club.uglyland.dao.PanNodeDAO;
import club.uglyland.dao.UserDao;
import club.uglyland.pojo.*;
import club.uglyland.util.SortUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 17:20
 * @Version : 1.0
 */
@Service
public class PanService {

    @Autowired
    PanNodeDAO panNodeDAO;

    @Autowired
    FileNodeDAO fileNodeDAO;

    @Autowired
    PanAuthorityDAO panAuthorityDAO;

    @Autowired
    UserDao userDao;

    public Map<String, Object> getPanList(Integer userId, Integer parentNodeId, Integer sortType) {
        List<PanVO> fileList = new LinkedList<>();
        List<PanVO> folderList = new LinkedList<>();

        List<PanNodeDO> panNodes = panNodeDAO.getPanListByParentId(userId, parentNodeId);
        for (PanNodeDO panNodeDO : panNodes) {
            PanVO panVO = new PanVO();
            Integer nodeId = panNodeDO.getNodeId();

            if(panNodeDO.getType()){
                FileNodeDO fileNodeDO = fileNodeDAO.getFileNodeById(nodeId);
                Long size = fileNodeDO.getSize();
                panVO.setFileSizeB(size);
                panVO.setFileSize(formatSize(size));
                panVO.setName(panNodeDO.getTitle());
                panVO.setNodeId(nodeId.toString());
                panVO.setUploadTime(formatDateTime(panNodeDO.getCreateTime()));
                panVO.setTime(panNodeDO.getCreateTime());
                panVO.setShared(fileNodeDO.getShared());
                fileList.add(panVO);
            }else{
                panVO.setFileSizeB(-1L);
                panVO.setFileSize("--");
                panVO.setUploadTime(formatDateTime(panNodeDO.getCreateTime()));
                panVO.setName(panNodeDO.getTitle());
                panVO.setNodeId(nodeId.toString());
                panVO.setTime(panNodeDO.getCreateTime());
                panVO.setShared(false);
                folderList.add(panVO);
            }
        }

        fileList.sort(SortUtil.getPanComparator(sortType));
        folderList.sort(SortUtil.getPanComparator(sortType));

        folderList.addAll(fileList);
        Map<String,Object> map = new HashMap<>();
        if(folderList.size()==0){
            map.put("code", ResponseCode.EMPTY_SET);
        }else {
            map.put("code", ResponseCode.SUCCESS);
            map.put("result",folderList);
        }
        return map;
    }


    public List<PanVO> getPanShareList(Integer sortType) {
        List<PanVO> fileList = new LinkedList<>();

        List<FileNodeDO> fileNodeDOs = fileNodeDAO.getSharedFile();
        for (FileNodeDO fileNodeDO : fileNodeDOs) {
            PanVO panVO = new PanVO();
            Integer nodeId = fileNodeDO.getNodeId();
            PanNodeDO panNodeDO = panNodeDAO.getPanNodeByNodeId(nodeId);
            Integer userId = panNodeDO.getUserId();
            String username = userDao.getUsernameById(userId);
            panVO.setShared(true);
            panVO.setFileSizeB(fileNodeDO.getSize());
            panVO.setFileSize(formatSize(fileNodeDO.getSize()));
            panVO.setUploadTime(formatDateTime(panNodeDO.getCreateTime()));
            panVO.setTime(panNodeDO.getCreateTime());
            panVO.setName(panNodeDO.getTitle());
            panVO.setNodeId(nodeId.toString());
            panVO.setUsername(username);

            fileList.add(panVO);
        }

        fileList.sort(SortUtil.getPanComparator(sortType));

        return fileList;
    }

    private String formatDateTime(Date createTime) {
        Instant instant = createTime.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        int year = localDateTime.getYear();
        int month = localDateTime.getMonth().getValue();
        int day = localDateTime.getDayOfMonth();

        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();

        return year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second;

    }

    private static String formatSize(Long size) {
        String unit;
        Long remain;
        Long number;
        Long B = 1L;
        Long KB = B * 1024L;
        Long MB = KB * 1024L;
        Long GB = MB * 1024L;
        if (size >= GB) {
            remain = size % GB;
            number = size / GB;
            remain = remain * 10 / GB;
            unit = " GB";
        } else if (size >= MB) {
            remain = size % MB;
            number = size / MB;
            remain = remain * 10 / MB;
            unit = " MB";
        } else if (size >= KB) {
            remain = size % KB;
            number = size / KB;
            remain = remain * 10 / KB;
            unit = " KB";
        }else{
            remain = 0L;
            number = size;
            unit = " B";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(number);
        if (remain != 0) {
            sb.append(".");
            sb.append(remain);
        }
        sb.append(unit);
        return sb.toString();
    }

    private String getFilePath(Integer nodeId){
        FileNodeDO fileNodeDO = fileNodeDAO.getFileNodeById(nodeId);
        Integer nodeName = fileNodeDO.getNodeName();
        return FileOperationPath.panFilePath+nodeName;
    }


    public ResponseEntity<byte[]> downloadFile(Integer nodeId) {

        String path = getFilePath(nodeId);
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        String originalName = panNodeDAO.getPanNodeByNodeId(nodeId).getTitle();
        String encodedFileName=null;
        try {
            encodedFileName = URLEncoder.encode(originalName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        headers.set("Content-Disposition", " filename=\"" + encodedFileName + "\""
                + "; filename*=UTF-8''" + encodedFileName);
        ResponseEntity<byte[]> result = null;
        try {
            result = new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Integer downloadCheck(Integer nodeId,Integer userId) {
        FileNodeDO fileNodeDO = fileNodeDAO.getFileNodeById(nodeId);
        Integer access =  fileNodeDO.getUserId().equals(userId)||fileNodeDO.getShared()?ResponseCode.SUCCESS:ResponseCode.PERMISSION_DENIED;
        String path = getFilePath(nodeId);
        File file = new File(path);
        if(!file.canRead()){
            access = ResponseCode.RESOURCES_DAMAGES;
        }
        return access;
    }

    public Integer delete(Integer nodeId,Integer userId) {
        if(userCheck(nodeId,userId)){
            long size = 0;
            PanNodeDO panNodeDO = panNodeDAO.getPanNodeByNodeId(nodeId);
            Boolean isFile = panNodeDO.getType();
            if(isFile){
                panNodeDAO.deleteByNodeId(nodeId);
                int result = ResponseCode.FAILED;
                if( deleteFile(nodeId)){
                    result = ResponseCode.SUCCESS;
                }
                FileNodeDO fileNodeDO = fileNodeDAO.getFileNodeById(nodeId);
                size=  fileNodeDO.getSize();
                fileNodeDAO.deleteByNodeId(nodeId);
                panAuthorityDAO.addUsed(userId,-size);
                return result;
            }else{
                Integer num = panNodeDAO.isEmptyParentId(nodeId);
                if(num == 0){
                    panNodeDAO.deleteByNodeId(nodeId);
                    return ResponseCode.SUCCESS;
                }else{
                    return ResponseCode.NOT_EMPTY_FOLDER;
                }
            }
        }else{
            return ResponseCode.PERMISSION_DENIED;
        }
    }

    private boolean deleteFile(Integer nodeId) {
        String path = getFilePath(nodeId);
        File file = new File(path);
        return file.delete();
    }


    private boolean userCheck(Integer nodeId,Integer userId){
        PanNodeDO panNodeDO = panNodeDAO.getPanNodeByNodeId(nodeId);
        return userId.equals(panNodeDO.getUserId());
    }

    public Integer createFolder(String folderName,Integer parentId,Integer userId) {
        try {
            panNodeDAO.insert(userId, parentId, false, folderName, new Date());
            return ResponseCode.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseCode.FAILED;
        }
    }

    public Integer getParentId(Integer nodeId){
        return panNodeDAO.getPanNodeByNodeId(nodeId).getParentId();
    }

    public Integer upload(MultipartFile file, Integer userId, Integer parentId){

        Long size = file.getSize();
        System.out.println("size = " + size);
        if(size> (long) 1024 * 1024 * 500){
            return ResponseCode.SINGLE_FILE_SIZE_EXCEED;
        }
        else if(!checkSize(userId,size)){
            return ResponseCode.USER_CAPACITY_EXCEED;
        }

        String originName = file.getOriginalFilename();
        PanNodeDO panNodeDO = new PanNodeDO();
        panNodeDO.setCreateTime(new Date());
        panNodeDO.setType(true);
        panNodeDO.setUserId(userId);
        panNodeDO.setTitle(originName);
        panNodeDO.setParentId(parentId);
        panNodeDAO.insertDO(panNodeDO);
        Integer nodeId = panNodeDO.getNodeId();


        FileNodeDO fileNodeDO =  new FileNodeDO();
        fileNodeDO.setNodeId(nodeId);
        fileNodeDO.setUserId(userId);
        fileNodeDO.setSize(size);
        fileNodeDO.setShared(false);
        fileNodeDAO.insert(fileNodeDO);
        String path = FileOperationPath.panFilePath+fileNodeDO.getNodeName();


        try{
            file.transferTo(Paths.get(path));
        }catch (Exception e){
            fileNodeDAO.deleteByNodeId(nodeId);
            panNodeDAO.deleteByNodeId(nodeId);
            return ResponseCode.FAILED;
        }

        panAuthorityDAO.addUsed(userId,size);

        return ResponseCode.SUCCESS;
    }

    private boolean checkSize(Integer userId,Long size) {
        PanAuthorityDO panAuthorityDO = panAuthorityDAO.getPanAuthorityByUserId(userId);
        long remain = panAuthorityDO.getCapacity()-panAuthorityDO.getUsed();
        return size <= remain;
    }


    public Map<String, Object> getSize(Integer user) {
        PanAuthorityDO panAuthorityDO = panAuthorityDAO.getPanAuthorityByUserId(user);
        Long totalSize = panAuthorityDO.getCapacity();
        String total = "总容量:"+totalSize+"B("+formatSize(totalSize)+") ";

        Long usedSize = panAuthorityDO.getUsed();
        String used = "已使用:"+usedSize+"B("+formatSize(usedSize)+") ";

        Long remainSize = totalSize - usedSize;
        String remain = "剩余:"+remainSize+"B("+formatSize(remainSize)+") ";

        Map<String,Object>result = new HashMap<>();
        result.put("total",total);
        result.put("used",used);
        result.put("remain",remain);
        return result;
    }


    public boolean share(Integer userId,Integer nodeId,boolean is){
        try{
            fileNodeDAO.changeShared(nodeId,userId,is);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean access(Integer userId,Integer nodeId) {
        return panNodeDAO.checkAccess(userId,nodeId)>0;
    }

    public boolean updatePanNodeName(Integer nodeId,String newName) {
        try{
            panNodeDAO.updateTitle(nodeId,newName);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
