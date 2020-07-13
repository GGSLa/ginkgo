package club.uglyland.pojo;

import java.util.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 17:56
 * @Version : 1.0
 */
public class PanNodeDO {
    Integer userId;
    Integer nodeId;
    Integer parentId;
    Boolean type;
    String title;
    Date createTime;

    public PanNodeDO(Integer userId, Integer nodeId, Integer parentId, Boolean type, String title , Date createTime) {
        this.userId = userId;
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.type = type;
        this.title = title;
        this.createTime = createTime;
    }

    public PanNodeDO() {
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean file) {
        type = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
