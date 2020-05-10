package club.uglyland.service;

import club.uglyland.dao.TaskDao;
import club.uglyland.dao.UserDao;
import club.uglyland.pojo.Task;
import club.uglyland.pojo.TaskForSearch;
import club.uglyland.responseCode.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * @Author : ZGQ
 * @Date : 2020/5/6 20:45
 * @Version : 1.0
 */
@Service
public class TaskService {

    @Autowired
    TaskDao taskDao;

    @Autowired
    HttpSession session;

    public Map<String,Object> getTask(Integer id,int state,int repeat,int nextTime,String search){
        if(id==null){
            Map<String,Object> ret= new HashMap<>();
            ret.put("code",ResponseCode.EMPTY_SET);
            return ret;
        }
        int plan = 0;
        while (repeat!=0){
            int tmp = repeat%10-1;
            tmp=1<<tmp;
            plan|=tmp;
            repeat/=10;
        }
        repeat=plan;
        int code=0;
        Date today = new Date(System.currentTimeMillis());
        List<Task>list = new LinkedList<>();
        if(nextTime==1){
            TaskForSearch taskForSearch = new TaskForSearch();
            taskForSearch.setUserId(id);
            taskForSearch.setSearch(search);
            taskForSearch.setRepeat(plan);
            list = taskDao.getAllTask(taskForSearch);
        }else {
            TaskForSearch task = new TaskForSearch();
            task.setUserId(id);
            task.setRepeat(plan);
            task.setState(state);
            task.setSearch(search);
            task.setFindingDate(today);
            setRvalue(task,today);
            List<Task>tmpList = taskDao.getTask(task);
            for(Task task1:tmpList){
                task1.setTime(today);
            }
            list.addAll(tmpList);
        }
        Map<String,Object>map=  new HashMap<>();
        if(list.size()==0){
            code= ResponseCode.EMPTY_SET;
        }
        map.put("code",code);
        if(code==0){
            if(nextTime==1){
                for(Task task:list){
                    task.setId(-1);
                }
            }
            map.put("list",list);
        }
        return map;
    }

    public List<Task> testDao(){
        TaskForSearch task = new TaskForSearch();
        task.setUserId(1);
        task.setState(1);
        task.setFindingDate(Date.valueOf("2020-5-8"));
        task.setRepeat(2);
        return taskDao.getTask(task);
    }

    private void setRvalue(TaskForSearch task,Date date){
        LocalDate localDate = date.toLocalDate();
        int rvalue3 = localDate.getDayOfWeek().getValue();
        int rvalue4 = localDate.getMonthValue();
        int rvalue5 = localDate.getDayOfMonth()+localDate.getMonthValue()*100;
        int rvalue1 = localDate.getYear()*10000+localDate.getDayOfMonth()+localDate.getMonthValue()*100;
        task.setrValue3(rvalue3);
        task.setrValue4(rvalue4);
        task.setrValue5(rvalue5);
        task.setrValue1(rvalue1);
    }

    public int addTask(String desc, Date time, int repeat){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            return 1;
        }

        LocalDate localDate=time.toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int date = localDate.getDayOfMonth();
        int day = localDate.getDayOfWeek().getValue();

        int rValue = 0;
        if (repeat == 1) {
            rValue=year*1000+month*100+date;
        }else if(repeat==3){
            rValue=day;
        }else if(repeat==4){
            rValue=date;
        }else if(repeat==5){
            rValue=month*100+date;
        }
        Task task=new Task();
        task.setUserId(userId);
        task.setDesc(desc);
        task.setTime(time);
        task.setRepeat(repeat);
        task.setrValue(rValue);
        task.setLastCompleted(new Date(0));
        try {
            taskDao.addTask(task);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
    }

    private boolean checkUserIdById(int id){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            return false;
        }
        Integer userIdOfId;
        try {
             userIdOfId = taskDao.selectUserIdById(id);
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
        if(!userIdOfId.equals(userId)){
            return false;
        }
        return true;
    }

    public int completeTask(int id){
        if(!checkUserIdById(id)){
            return 1;
        }
        taskDao.completeTask(id);
        return 0;
    }

    public int deleteTask(int id){
        if(!checkUserIdById(id)){
            return 1;
        }
        taskDao.deleteTask(id);
        return 0;
    }
}
