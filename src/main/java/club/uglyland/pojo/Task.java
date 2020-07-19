package club.uglyland.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/5/6 20:48
 * @Version : 1.0
 */
@Data
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
}
