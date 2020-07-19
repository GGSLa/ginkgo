package club.uglyland.service;

import club.uglyland.dao.TaskDao;
import club.uglyland.pojo.Task;
import club.uglyland.pojo.TaskForSearch;
import club.uglyland.application.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    ClassPathXmlApplicationContext dateIoc = new ClassPathXmlApplicationContext("standards/date.xml");

    public Map<String, Object> getTask(Integer id, int state, int repeat, int nextTime, String search) {
        if (id == null) {
            Map<String, Object> ret = new HashMap<>();
            ret.put("code", ResponseCode.EMPTY_SET);
            return ret;
        }
        int plan = 0;
        while (repeat != 0) {
            int tmp = repeat % 10 - 1;
            tmp = 1 << tmp;
            plan |= tmp;
            repeat /= 10;
        }
        repeat = plan;
        int code = 0;
        Date today = (Date) dateIoc.getBean("dateOfToday");
        List<Task> list = new LinkedList<>();
        if (nextTime == 1) {
            TaskForSearch taskForSearch = new TaskForSearch();
            taskForSearch.setUserId(id);
            taskForSearch.setSearch(search);
            taskForSearch.setRepeat(plan);
            list = taskDao.getAllTask(taskForSearch);
        } else {
            TaskForSearch task = new TaskForSearch();
            task.setUserId(id);
            task.setRepeat(plan);
            task.setState(state);
            task.setSearch(search);
            task.setFindingDate(today);
            setRvalue(task, today);
            List<Task> tmpList = taskDao.getTask(task);
            list.addAll(tmpList);
        }
        Map<String, Object> map = new HashMap<>();
        if (list.size() == 0) {
            code = ResponseCode.EMPTY_SET;
        }
        map.put("code", code);
        if (code == 0) {
            if (nextTime == 1) {
                for (Task task : list) {
                    task.setRValue(-1);
                }
            }
            map.put("list", list);
        }
        return map;
    }

    private void setRvalue(TaskForSearch task, Date date) {
        LocalDate localDate = date.toLocalDate();
        int rvalue3 = localDate.getDayOfWeek().getValue();
        int rvalue4 = localDate.getMonthValue();
        int rvalue5 = localDate.getDayOfMonth() + localDate.getMonthValue() * 100;
        int rvalue1 = localDate.getYear() * 10000 + localDate.getDayOfMonth() + localDate.getMonthValue() * 100;
        task.setRValue3(rvalue3);
        task.setRValue4(rvalue4);
        task.setRValue5(rvalue5);
        task.setRValue1(rvalue1);
    }

    public int addTask(String desc, Date time, int repeat) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return 1;
        }

        LocalDate localDate = time.toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int date = localDate.getDayOfMonth();
        int day = localDate.getDayOfWeek().getValue();

        int rValue = 0;
        if (repeat == 1) {
            rValue = year * 10000 + month * 100 + date;
        } else if (repeat == 3) {
            rValue = day;
        } else if (repeat == 4) {
            rValue = date;
        } else if (repeat == 5) {
            rValue = month * 100 + date;
        }
        Task task = new Task();
        task.setUserId(userId);
        task.setDesc(desc);
        task.setTime(time);
        task.setRepeat(repeat);
        task.setRValue(rValue);
        task.setLastCompleted(new Date(0));
        try {
            taskDao.addTask(task);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    private boolean checkUserIdById(int id) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return false;
        }
        Integer userIdOfId;
        try {
            userIdOfId = taskDao.selectUserIdById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        if (!userIdOfId.equals(userId)) {
            return false;
        }
        return true;
    }

    public int completeTask(int id) {
        if (!checkUserIdById(id)) {
            return 1;
        }
        taskDao.completeTask(id, (Date) dateIoc.getBean("dateOfToday"));
        return 0;
    }

    public int deleteTask(int id) {
        if (!checkUserIdById(id)) {
            return 1;
        }
        taskDao.deleteTask(id);
        return 0;
    }
}
