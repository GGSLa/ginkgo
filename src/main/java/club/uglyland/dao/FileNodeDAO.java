package club.uglyland.dao;

import club.uglyland.pojo.FileNodeDO;

import java.util.List;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 17:32
 * @Version : 1.0
 */
public interface FileNodeDAO {
    FileNodeDO getFileNodeById(Integer node_id);
    void deleteByNodeId(Integer nodeId);
    void insert(FileNodeDO fileNodeDO);
    void changeShared(Integer nodeId,Integer userId,boolean option);
    List<FileNodeDO> getSharedFile();
}
