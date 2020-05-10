package club.uglyland.service;

import club.uglyland.dao.UserDao;
import club.uglyland.responseCode.ResponseCode;
import club.uglyland.util.MD5Util;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : ZGQ
 * @Date : 2020/5/4 22:06
 * @Version : 1.0
 */
@Service
public class UserAccountService {

    @Autowired
    UserDao userDao;

    @Autowired
    protected HttpSession session;

    public Map<String,Object> register(String username, String password){
        password = MD5Util.MD5(password);
        int code=ResponseCode.SUCCESS;
        try {
            userDao.insertUser(username,password);
        }
        catch (Exception e){
            if(e.getCause().getMessage().startsWith("Duplicate entry")){
                code = ResponseCode.DUPLICATE_NAME;
                System.err.println("用户名重复");
            }else{
                code=ResponseCode.FAILED;
                e.printStackTrace();
            }
        }
        Map<String,Object>map = new HashMap<>();
        System.out.println("code = " + code);
        map.put("code",code);
        return map;
    }

    public Map<String,Object>login(String username,String password){
        password=MD5Util.MD5(password);
        int code=ResponseCode.PASSWORD_WRONG;
        int id=-1;
        try{
            id=userDao.getIdByPassword(username,password);
            code=ResponseCode.SUCCESS;
        }catch (Exception e){
            System.err.println(username+" 密码错误");
        }

        Map<String,Object> map=new HashMap<>();
        map.put("code",code);
        if(code==ResponseCode.SUCCESS){
            session.setAttribute("userId",id);
            session.setAttribute("username",username);
            map.put("userId",id);
        }
        return map;
    }

    public void logout(){
        session.setAttribute("userId",-10086);
        session.setAttribute("username",null);
    }
}
