package club.uglyland.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 17:21
 * @Version : 1.0
 */
@Data
public class PanVO {
    private String nodeId;
    private String name;
    private String uploadTime;
    private String fileSize;
    private Boolean shared;
    private String username;
    @JsonIgnore
    private Long fileSizeB;
    @JsonIgnore
    private Date time;
}
