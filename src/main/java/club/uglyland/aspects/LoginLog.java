package club.uglyland.aspects;
import club.uglyland.application.FileOperationPath;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * @Author : ZGQ
 * @Date : 2020/5/14 14:08
 * @Version : 1.0
 */
@Aspect
@Component
public class LoginLog {

    @AfterReturning(value = "execution(public * club.uglyland.service.UserAccountService.login(..))",returning = "result")
    public void LoginSuccess(JoinPoint joinPoint, Map<String,Object> result){
        if((result.get("code")).equals(0)){
            LocalDate date=LocalDate.now();
            String filename = date.toString();
            filename = filename.replace('-','_');
            filename+="_loginSuccess.txt";
            FileOutputStream fos=null;
            try {
                String filepath = FileOperationPath.logPath +filename;
                fos = new FileOutputStream(filepath,true);
                LocalTime localTime=LocalTime.now();
                String message=(String)joinPoint.getArgs()[0]+" "+localTime+"\n";
                fos.write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
