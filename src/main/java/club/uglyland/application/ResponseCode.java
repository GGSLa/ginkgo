package club.uglyland.application;

/**
 * @Author : ZGQ
 * @Date : 2020/5/4 21:04
 * @Version : 1.0
 */
public interface ResponseCode {
    int FAILED = -1;
    int SUCCESS = 0;
    int DUPLICATE_NAME=1;
    int PASSWORD_WRONG=2;
    int EMPTY_SET=3;
    int WRONG_VERIFY_CODE=4;
    int REQUEST_LIMITED=5;
    int DUPLICATE_MAIL=6;

}
