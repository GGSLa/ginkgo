package club.uglyland.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 17:21
 * @Version : 1.0
 */
public class PanVO {
    String nodeId;
    String name;
    String uploadTime;
    String fileSize;
    @JsonIgnore
    Long fileSizeB;
    @JsonIgnore
    Date time;

    public PanVO(String nodeId, String name, String uploadTime, String fileSize, Long fileSizeB, Date time) {
        this.nodeId = nodeId;
        this.name = name;
        this.uploadTime = uploadTime;
        this.fileSize = fileSize;
        this.fileSizeB = fileSizeB;
        this.time = time;
    }

    public PanVO() {
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSizeB() {
        return fileSizeB;
    }

    public void setFileSizeB(Long fileSizeB) {
        this.fileSizeB = fileSizeB;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
