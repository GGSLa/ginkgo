package club.uglyland.controller;

import club.uglyland.application.ResponseCode;
import club.uglyland.pojo.PanVO;
import club.uglyland.service.PanService;
import club.uglyland.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author : ZGQ
 * @Date : 2020/7/7 22:57
 * @Version : 1.0
 */
@Controller
public class PanController {

    @Autowired
    PanService panService;

    @ResponseBody
    @RequestMapping("/pan/add/file")
    public Map<String, Object> addFile(int parent_node, MultipartFile file) {
        return null;
    }


    @ResponseBody
    @RequestMapping("/getPanList")
    public Map<String, Object> getPanList(Integer nodeId, Integer sortType, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return panService.getPanList(userId, nodeId, sortType);
    }
    @ResponseBody
    @RequestMapping("/getPanSharedList")
    public Map<String, Object> getPanSharedList(Integer sortType) {

        int code;
        List<PanVO> panShareList =null;
        if(sortType==null){
            sortType=0;
        }
        try{
            panShareList = panService.getPanShareList(sortType);
            assert panShareList != null;
        }catch (Exception e){
            code = ResponseCode.FAILED;
            return CollectionsUtil.getMap("code",code);
        }

        if(panShareList.size()==0){
            code = ResponseCode.EMPTY_SET;
            return CollectionsUtil.getMap("code",code);
        }

        code=ResponseCode.SUCCESS;
        return CollectionsUtil.getMap("code",code,"result",panShareList);
    }

    @ResponseBody
    @RequestMapping("/download/check/{nodeId}")
    public Integer downloadCheck(@PathVariable("nodeId") Integer nodeId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return panService.downloadCheck(nodeId,userId);
    }


    @ResponseBody
    @RequestMapping("/download/{nodeId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("nodeId") Integer nodeId,HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if(panService.downloadCheck(nodeId,userId)!=0){
            return null;
        }
        return panService.downloadFile(nodeId);
    }

    @ResponseBody
    @RequestMapping("/delete/{nodeId}")
    public Integer delete(@PathVariable("nodeId") Integer nodeId,HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return panService.delete(nodeId,userId);
    }

    @ResponseBody
    @RequestMapping("/create/folder")
    public Integer createFolder(String folderName,Integer parentId,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        return panService.createFolder(folderName,parentId,userId);

    }

    @ResponseBody
    @RequestMapping("/parentid")
    public Integer parentId(Integer nodeId){
        return panService.getParentId(nodeId);
    }

    @RequestMapping("/upload")
    public Integer upload(@RequestParam("uploadFile") MultipartFile file, HttpSession session, Integer parentId){
        Integer user = (Integer) session.getAttribute("userId");
        return panService.upload(file, user, parentId);
    }


    @ResponseBody
    @RequestMapping("/userSize")
    public Map<String,Object> getUserSize(HttpSession session){
        Integer user = (Integer) session.getAttribute("userId");
        return panService.getSize( user);
    }

    @ResponseBody
    @RequestMapping("/share/{nodeId}")
    public Integer share(HttpSession session,@PathVariable("nodeId") Integer nodeId){
        if(panService.share((Integer) session.getAttribute("userId"),nodeId,true)) {
            return ResponseCode.SUCCESS;
        }else{
            return ResponseCode.FAILED;
        }
    }

    @ResponseBody
    @RequestMapping("/unshare/{nodeId}")
    public Integer unshare(HttpSession session,@PathVariable("nodeId") Integer nodeId){
        if(panService.share((Integer) session.getAttribute("userId"),nodeId,false)) {
            return ResponseCode.SUCCESS;
        }else{
            return ResponseCode.FAILED;
        }
    }

}
