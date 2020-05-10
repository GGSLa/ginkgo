package club.uglyland.controller;
import club.uglyland.pojo.Task;
import club.uglyland.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @ResponseBody
    @RequestMapping("/test")
    public List<Task> getTaskTest(){
        return taskService.testDao();
    }

    @RequestMapping("/addTask")
    public String addTask(String desc, Date time,int repeat){
        int ret = taskService.addTask(desc,time,repeat);
        if(ret==1){
            return "redirect:pages/login.jsp";
        }
        return  "redirect:pages/showtask.jsp";
    }

    @RequestMapping("/complete")
    public String completeTask(Integer id){
        int ret =taskService.completeTask(id);
        if(ret==1){
            return "redirect:pages/login.jsp";
        }
        return  "redirect:pages/showtask.jsp";
    }
    @RequestMapping("/delete")
    public String deleteTask(Integer id){
        int ret =taskService.deleteTask(id);
        if(ret==1){
            return "redirect:pages/login.jsp";
        }
        return  "redirect:pages/showtask.jsp";
    }
}
