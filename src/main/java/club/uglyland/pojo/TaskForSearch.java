package club.uglyland.pojo;

import java.sql.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/5/8 16:11
 * @Version : 1.0
 */
public class TaskForSearch {
    private Integer id;
    private Integer userId;
    private String desc;
    private Date time;
    private Integer state;
    private Integer repeat;
    private Integer rValue1;
    private Integer rValue3;
    private Integer rValue4;
    private Integer rValue5;
    private Date findingDate;
    private String search;

    public Integer getrValue1() {
        return rValue1;
    }

    public void setrValue1(Integer rValue1) {
        this.rValue1 = rValue1;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Integer getrValue3() {
        return rValue3;
    }

    public void setrValue3(Integer rValue3) {
        this.rValue3 = rValue3;
    }

    public Integer getrValue4() {
        return rValue4;
    }

    public void setrValue4(Integer rValue4) {
        this.rValue4 = rValue4;
    }

    public Integer getrValue5() {
        return rValue5;
    }

    public void setrValue5(Integer rValue5) {
        this.rValue5 = rValue5;
    }

    public Date getFindingDate() {
        return findingDate;
    }

    public void setFindingDate(Date findingDate) {
        this.findingDate = findingDate;
    }
}
