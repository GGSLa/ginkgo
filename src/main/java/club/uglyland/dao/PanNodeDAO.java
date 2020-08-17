package club.uglyland.dao;

import club.uglyland.pojo.PanNodeDO;

import java.util.Date;
import java.util.List;

/**
 * @Author : ZGQ
 * @Date : 2020/7/5 16:45
 * @Version : 1.0
 */
public interface PanNodeDAO {

    List<PanNodeDO> getPanListByParentId(Integer userId,Integer parentId);
    PanNodeDO getPanNodeByNodeId(Integer nodeId);
    Integer isEmptyParentId(Integer nodeId);
    void deleteByNodeId(Integer nodeId);
    void insert(Integer userId, Integer parentId, Boolean isFile, String Title, Date date);
    void insertDO(PanNodeDO panNodeDO);
    Integer checkAccess(Integer userId,Integer nodeId);
    void updateTitle(Integer nodeId,String newName);
}
