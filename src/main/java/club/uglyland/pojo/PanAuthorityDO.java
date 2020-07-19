package club.uglyland.pojo;

import lombok.Data;

/**
 * @Author : ZGQ
 * @Date : 2020/7/12 21:45
 * @Version : 1.0
 */

@Data
public class PanAuthorityDO {
    private Integer userId;
    private Long capacity;
    private Long used;
}
