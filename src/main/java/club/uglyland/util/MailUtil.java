package club.uglyland.util;

import club.uglyland.application.FileOperationPath;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

/**
 * @Author : ZGQ
 * @Date : 2020/6/22 22:58
 * @Version : 1.0
 */
public class MailUtil {


    static String user;
    static String password;
    static Properties properties;

    static {
        Properties fileProperties = new Properties();
         // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in;
        try {
            in = MD5Util.class.getClassLoader().getResourceAsStream("property"+ FileOperationPath.splitor +"datasource.properties");
            fileProperties.load(in);
        } catch (FileNotFoundException e) {
            System.err.println("配置文件未找到");
        } catch (IOException e) {
            System.err.println("文件读取失败");
        }

        user = fileProperties.getProperty("mail.user");
        password=fileProperties.getProperty("mail.password");
        properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用


    }

    public static void sendMail(String receiver,String content) throws MessagingException {

        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);

        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(user));

        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiver)});
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
        // 设置邮件标题
        message.setSubject("ZGQ`SPACE 验证码 "+ LocalDate.now().toString());
        // 设置邮件内容
        if("爱你爱你".equals(content)){
            message.setText(content);
        }else {
            message.setText("验证码内容为： " + content + ",有效时间五分钟\n如果您没有访问过我的网页，请直接忽视");
        }
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect(user,password);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
