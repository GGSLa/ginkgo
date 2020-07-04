package club.uglyland.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.PublicKey;
import java.util.Random;

/**
 * @Author : ZGQ
 * @Date : 2020/6/23 22:14
 * @Version : 1.0
 */
public class VerifyCodeUtil {


    private static String getVerifyCode(int len){
        StringBuilder sb = new StringBuilder();
        Random random = new ClassPathXmlApplicationContext("standards/util.xml").getBean(Random.class);

        for(int i=0;i<len;i++){
            int num = random.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

    public static String getVerifyCode(){
        return getVerifyCode(6);
    }

}

