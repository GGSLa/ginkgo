package club.uglyland.dao;

import club.uglyland.pojo.Task;
import club.uglyland.pojo.TaskForSearch;

import java.sql.Date;
import java.util.List;

/**
 * @Author : ZGQ
 * @Date : 2020/5/6 20:59
 * @Version : 1.0
 */
public interface TaskDao {
    List<Task> getTask(TaskForSearch task);
    List<Task> getAllTask(TaskForSearch task);
    void addTask(Task task);
    void completeTask(int id);
    void deleteTask(int id);
    int selectUserIdById(int id);
}
