package club.uglyland.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 17:56
 * @Version : 1.0
 */
@Data
public class PanNodeDO {
    Integer userId;
    Integer nodeId;
    Integer parentId;
    Boolean type;
    String title;
    Date createTime;
}
