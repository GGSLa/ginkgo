package club.uglyland.util;

import javax.mail.MessagingException;

/**
 * @Author : ZGQ
 * @Date : 2020/6/24 0:02
 * @Version : 1.0
 */
public class MailThread implements Runnable{

    String mail;
    String verifyCode;

    public MailThread(String mail, String verifyCode) {
        this.mail = mail;
        this.verifyCode = verifyCode;
    }

    @Override
    public void run() {
        try {
            MailUtil.sendMail(mail,verifyCode);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
