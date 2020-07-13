package club.uglyland.application;

/**
 * @Author : ZGQ
 * @Date : 2020/5/4 21:04
 * @Version : 1.0
 */
public interface ResponseCode {
    int FAILED = -1;
    int SUCCESS = 0;
    /**
     * 用户名重复
     */
    int DUPLICATE_NAME = 1;

    /**
     * 密码错误
     */
    int PASSWORD_WRONG = 2;

    /**
     * 查询结果为空
     */
    int EMPTY_SET = 3;

    /**
     * 验证码错误
     */
    int WRONG_VERIFY_CODE = 4;

    /**
     * 限流
     */
    int REQUEST_LIMITED = 5;

    /**
     * 邮箱重复
     */
    int DUPLICATE_MAIL = 6;

    /**
     * 没有权限
     */
    int PERMISSION_DENIED = 7;

    /**
     * 资源受损
     */
    int RESOURCES_DAMAGES = 8;

    /**
     * 文件夹不为空
     */
    int NOT_EMPTY_FOLDER = 9;

    /**
     * 单个文件超出大小
     */
    int SINGLE_FILE_SIZE_EXCEED = 10;

    /**
     * 用户空间不足
     */
    int USER_CAPACITY_EXCEED = 11;
}
