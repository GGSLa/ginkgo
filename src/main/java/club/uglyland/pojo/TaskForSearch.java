package club.uglyland.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/5/8 16:11
 * @Version : 1.0
 */
@Data
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
}
