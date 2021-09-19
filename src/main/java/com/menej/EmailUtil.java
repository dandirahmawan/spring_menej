package com.menej;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    public void sendEmial(Session session, String toEmail, String subject, String body){
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("menejproyek@gmail.com", "Menej"));
            msg.setReplyTo(InternetAddress.parse("no_reply", false));
            msg.setSubject(subject, "UTF-8");
            // msg.setText(body, "UTF-8");
            msg.setContent(body, "text/html");
            
            Date date = new Date();
            msg.setSentDate(date);   
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}