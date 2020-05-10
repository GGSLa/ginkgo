package club.uglyland.controller;

import club.uglyland.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : ZGQ
 * @Date : 2020/5/4 20:58
 * @Version : 1.0
 */
@Controller
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @ResponseBody
    @RequestMapping("/register")
    public Map<String, Object> register(String username, String password){
        return userAccountService.register(username,password);
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object>login(String username,String password){
        return userAccountService.login(username,password);
    }

    @RequestMapping("/logout")
    public String logout(){
        userAccountService.logout();
        return "redirect:index.jsp";
    }

}
