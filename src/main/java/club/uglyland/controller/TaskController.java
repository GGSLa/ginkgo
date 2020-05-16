package club.uglyland.controller;
import club.uglyland.pojo.Task;
import club.uglyland.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author : ZGQ
 * @Date : 2020/5/6 20:39
 * @Version : 1.0
 */
@Controller
public class TaskController {

    @Autowired
    HttpSession session;

    @Autowired
    TaskService taskService;

    @ResponseBody
    @RequestMapping("/getTask")
    public Map<String,Object> getTask(int state,int repeat,int nextTime,String search){
        Integer id= (Integer) session.getAttribute("userId");
        return taskService.getTask(id,state,repeat,nextTime,search);
    }

    @RequestMapping("/addTask")
    public String addTask(String desc, Date time,int repeat){
        int ret = taskService.addTask(desc,time,repeat);
        if(ret==1){
            return "redirect:pages/login.jsp";
        }
        return  "redirect:pages/showtask.jsp";
    }

    @ResponseBody
    @RequestMapping("/complete")
    public int completeTask(Integer id){
        return taskService.completeTask(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public int deleteTask(Integer id){
        return taskService.deleteTask(id);
    }
}
