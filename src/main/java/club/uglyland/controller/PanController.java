package club.uglyland.controller;

import club.uglyland.application.FileOperationPath;
import club.uglyland.service.PanService;
import org.apache.logging.log4j.core.impl.ReusableLogEventFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Iterator;
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
    @RequestMapping("/download/check/{nodeId}")
    public Integer downloadCheck(@PathVariable("nodeId") Integer nodeId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return panService.downloadCheck(nodeId,userId);
    }


    @ResponseBody
    @RequestMapping("/download/{nodeId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("nodeId") Integer nodeId) {
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

}
