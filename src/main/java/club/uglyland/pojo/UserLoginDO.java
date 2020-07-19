package club.uglyland.pojo;

import lombok.Data;

/**
 * @Author : ZGQ
 * @Date : 2020/7/13 23:04
 * @Version : 1.0
 */
@Data
public class UserLoginDO {
    private Integer id;
    private String handle;
    private String password;
    private String mail;
}
