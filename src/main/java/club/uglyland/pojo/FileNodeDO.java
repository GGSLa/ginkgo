package club.uglyland.pojo;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 19:37
 * @Version : 1.0
 */
public class FileNodeDO {
    Integer nodeId;
    Integer userId;
    Integer nodeName;
    Long size;
    Boolean shared;

    public FileNodeDO(Integer nodeId, Integer userId, Integer nodeName, Long size, Boolean shared) {
        this.nodeId = nodeId;
        this.userId = userId;
        this.nodeName = nodeName;
        this.size = size;
        this.shared = shared;
    }

    public FileNodeDO(){

    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNodeName() {
        return nodeName;
    }

    public void setNodeName(Integer nodeName) {
        this.nodeName = nodeName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }
}

