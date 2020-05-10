package club.uglyland.pojo;

import java.sql.Date;
import java.util.Objects;

/**
 * @Author : ZGQ
 * @Date : 2020/5/6 20:48
 * @Version : 1.0
 */
public class Task {

    public static final int ONCE = 1;
    public static final int EVERYDAY = 2;
    public static final int EVERYWEEK =3;
    public static final int EVERYMOUTH =4;
    public static final int EVERYYEAR =5;

    private Integer id;
    private Integer userId;
    private String desc;
    private Date time;
    private Integer frequency;
    private Integer repeat;
    private Integer rValue;
    private Date lastCompleted;

    public Task(int id, int userId, String desc, Date time, int frequency, int repeat, int rValue,Date lastCompleted) {
        this.id = id;
        this.userId = userId;
        this.desc = desc;
        this.time = time;
        this.frequency = frequency;
        this.repeat = repeat;
        this.rValue = rValue;
        this.lastCompleted=lastCompleted;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getrValue() {
        return rValue;
    }

    public void setrValue(int rValue) {
        this.rValue = rValue;
    }

    public Date getLastCompleted() {
        return lastCompleted;
    }

    public void setLastCompleted(Date lastCompleted) {
        this.lastCompleted = lastCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                userId == task.userId &&
                frequency == task.frequency &&
                repeat == task.repeat &&
                rValue == task.rValue &&
                Objects.equals(desc, task.desc) &&
                Objects.equals(time, task.time) &&
                Objects.equals(lastCompleted, task.lastCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, desc, time, frequency, repeat, rValue, lastCompleted);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", desc='" + desc + '\'' +
                ", time=" + time +
                ", frequency=" + frequency +
                ", repeat=" + repeat +
                ", rValue=" + rValue +
                ", lastCompleted=" + lastCompleted +
                '}';
    }
}
