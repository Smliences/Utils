package com.sml;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class EmailUtil {
    public static void sendEmail(String fromAccount, String fromPassword, String content, String... toAccount) throws Exception {
        String smtpHost;
        if (fromAccount.endsWith("@163.com")){
            smtpHost = "smtp.163.com";
        } else {
            smtpHost = "smtp.qq.com";
        }

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol","smtp");
        props.setProperty("mail.smtp.host",smtpHost);
        props.setProperty("mail.smtp.auth","true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage mimeMessage = createMimeMessage(session, fromAccount, content, toAccount);

        Transport transport = session.getTransport();
        transport.connect(fromAccount,fromPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }

    public static MimeMessage createMimeMessage(Session session, String fromAccount, String content, String[] toAccount) throws Exception {
        MimeMessage mimeMessage = new MimeMessage(session);

        mimeMessage.setSubject("test");
        mimeMessage.setSentDate(new Date());
        mimeMessage.setFrom(new InternetAddress(fromAccount,"test","utf-8"));
        mimeMessage.setContent(content,"text/html;charset=utf-8");
        for (String s : toAccount) {
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(s,"test1","utf-8"));
        }
        mimeMessage.saveChanges();
        return mimeMessage;
    }
    public static void main(String[] args) throws Exception {
        sendEmail("s571777232@163.com", "71616997290123", "ceshi", "571777232@qq.com");
    }
}
