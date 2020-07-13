package club.uglyland.service;

import club.uglyland.application.CommonProperties;
import club.uglyland.dao.PanAuthorityDAO;
import club.uglyland.dao.UserDao;
import club.uglyland.application.ResponseCode;
import club.uglyland.pojo.UserLoginDO;
import club.uglyland.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author : ZGQ
 * @Date : 2020/5/4 22:06
 * @Version : 1.0
 */
@Service
public class UserAccountService {

    @Autowired
    private UserDao userDao;

    @Autowired
    protected HttpSession session;

    @Autowired
    private PanAuthorityDAO panAuthorityDAO;

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("standards/util.xml");

    public Map<String,Object> register(String username, String password,String mail,String verifyCode){

        Jedis jedis=new Jedis(CommonProperties.serverHost);
        String storedCode = jedis.get(mail);
        if(storedCode==null||!storedCode.equals(verifyCode)){
            Map<String,Object>result = new HashMap<>();
            result.put("code",ResponseCode.WRONG_VERIFY_CODE);
            return result;
        }
        password = MD5Util.MD5(password);
        int code=ResponseCode.SUCCESS;
        try {
            UserLoginDO loginDO = new UserLoginDO();
            loginDO.setHandle(username);
            loginDO.setMail(mail);
            loginDO.setPassword(password);
            userDao.insertUserDO(loginDO);
            panAuthorityDAO.insert(loginDO.getId());
        }
        catch (Exception e){
            if(e.getCause().getMessage().startsWith("Duplicate entry")){
                if(userDao.checkUsername(username)!=0) {
                    code = ResponseCode.DUPLICATE_NAME;
                    System.err.println("用户名重复");
                }else if(userDao.checkMail(mail)!=0){
                    code = ResponseCode.DUPLICATE_MAIL;
                    System.err.println("邮箱重复");
                }else{
                    code=ResponseCode.FAILED;
                }
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

    public Map<String,Object> getVerifyCode(String mail, HttpServletRequest request){
        if(userDao.checkMail(mail)>0){
            Map<String,Object>map=  new HashMap<>();
            map.put("code",ResponseCode.DUPLICATE_MAIL);
            return map;
        }
        Jedis jedis = new Jedis(CommonProperties.serverHost);
        HashMap<String,Object> map = new HashMap<>();
        String ip = HttpUtil.getIp(request);
        Long ttl = jedis.ttl(ip);
        if(ttl>0){
            map.put("code",ResponseCode.REQUEST_LIMITED);
            return map;
        }else{
            jedis.set(ip,"1");
            jedis.expire(ip,60);
        }
        String verifyCode= VerifyCodeUtil.getVerifyCode();
        jedis.set(mail,verifyCode);
        jedis.expire(mail,60*5);
        try {
            MailThread mailThread = new MailThread(mail,verifyCode);
            context.getBean(ThreadPoolExecutor.class).execute(mailThread);
        } catch (Exception e) {
            System.out.println("发送失败");
            map.put("code",ResponseCode.FAILED);
        }
        map.put("code",0);
        return map;
    }
}
