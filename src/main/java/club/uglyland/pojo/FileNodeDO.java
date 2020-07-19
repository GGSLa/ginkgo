package club.uglyland.pojo;

import lombok.Data;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 19:37
 * @Version : 1.0
 */
@Data
public class FileNodeDO {
    private Integer nodeId;
    private Integer userId;
    private Integer nodeName;
    private Long size;
    private Boolean shared;
}

