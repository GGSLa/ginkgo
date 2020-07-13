package club.uglyland.pojo;

/**
 * @Author : ZGQ
 * @Date : 2020/7/13 23:04
 * @Version : 1.0
 */
public class UserLoginDO {
    Integer id;
    String handle;
    String password;
    String mail;

    public UserLoginDO(Integer id, String handle, String password, String mail) {
        this.id = id;
        this.handle = handle;
        this.password = password;
        this.mail = mail;
    }

    public UserLoginDO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
