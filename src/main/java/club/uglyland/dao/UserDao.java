package club.uglyland.dao;

import club.uglyland.pojo.UserLoginDO;
import org.springframework.stereotype.Repository;

/**
 * @Author : ZGQ
 * @Date : 2020/5/4 22:29
 * @Version : 1.0
 */

public interface UserDao {
    void insertUser(String handle,String password,String mail);
    void insertUserDO(UserLoginDO userLoginDO);
    int getIdByPassword(String handle,String password);
    String getUsernameById(Integer id);
    int checkUsername(String username);
    int checkMail(String mail);
}
