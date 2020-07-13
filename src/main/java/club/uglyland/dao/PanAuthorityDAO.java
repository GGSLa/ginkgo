package club.uglyland.dao;

import club.uglyland.pojo.PanAuthorityDO;

/**
 * @Author : ZGQ
 * @Date : 2020/7/12 21:45
 * @Version : 1.0
 */
public interface PanAuthorityDAO {
    PanAuthorityDO getPanAuthorityByUserId(Integer userId);
    void addUsed(Integer userId,Long size);
    void insert(Integer userId);
}
