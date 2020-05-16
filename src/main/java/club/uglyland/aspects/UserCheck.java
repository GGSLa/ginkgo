package club.uglyland.aspects;

import club.uglyland.application.ResponseCode;
import club.uglyland.dao.UserDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Author : ZGQ
 * @Date : 2020/5/14 22:27
 * @Version : 1.0
 */
//用于验证用户身份
@Aspect
@Component
public class UserCheck {

    @Autowired
    HttpSession session;

    @Autowired
    UserDao userDao;

    @Around("execution(public * club.uglyland.service.TaskService.*(..))")
    public Object checkLogin(ProceedingJoinPoint pjp){
        Integer id = (Integer) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

        String trueUsername = userDao.getUsernameById(id);
        HashMap<String,Object>map=new HashMap<>();
        if(username.equals(trueUsername)){
            try {
                return pjp.proceed(pjp.getArgs());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                map.put("code", ResponseCode.FAILED);
            }
        }else{
            map.put("code", ResponseCode.FAILED);
        }

        System.err.println("FAILED");
        String returnType = pjp.getSignature().toString().split(" ")[0];
        switch (returnType) {
            case "Map":
                return map;
            case "int":
                return -1;
            case "String":
                return "redirect:pages/login.jsp";
        }
        return -1;
    }
}
