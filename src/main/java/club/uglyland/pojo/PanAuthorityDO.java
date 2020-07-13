package club.uglyland.pojo;

import javax.print.attribute.standard.PageRanges;

/**
 * @Author : ZGQ
 * @Date : 2020/7/12 21:45
 * @Version : 1.0
 */
public class PanAuthorityDO {
    private Integer userId;
    private Long capacity;
    private Long used;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getUsed() {
        return used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }
}
