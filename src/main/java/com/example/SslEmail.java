package com.example;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import java.util.Properties;

public class SslEmail {

    public void sendEmail(String emailTo, String codeConfirm){
        String fromEmail = "dandirahmawan95@gmail.com";
        String password = "majulancar";
        String toEmail = emailTo;
 
        System.out.println("start sender email");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
        };

        String message = "<table>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td><img src=\"https://menej.com/assets/images/menej_285e8e.png\"></td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td>\r\n" + 
		        		"				<strong><span style=\"color: #386384\">menej</span> invite you to colaborating in menej</strong><br>\r\n" + 
		        		"				Please click button bellow for accepting this invitation<br>\r\n" + 
		        		"				this email will expired in 24 hours.\r\n" + 
		        		"			</td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td>\r\n" + 
		        		"				<a href=\"http://localhost:3000/invitation?conf="+codeConfirm+"\"><button style=\"padding: 10px;border:none;background: #386384;color: #FFF;border-radius: 5px;\">Accept</button></a>\r\n" + 
		        		"			</td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td>\r\n" + 
		        		"				<div style=\"border-top: 1px solid #CCC;color: #a0a0a0;padding-top: 10px;font-size: 11px;margin-top: 10px;\">\r\n" + 
		        		"					This is email confirmation by menej for "+emailTo+"\r\n" + 
		        		"				</div>\r\n" + 
		        		"			</td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"	</table>";

        Session session = Session.getDefaultInstance(props, auth);
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendEmial(session, toEmail, "Invitation", message);
    }
    
    public void sendEmailRegistration(String emailTo, String codeConfirm){
        String fromEmail = "dandirahmawan95@gmail.com";
        String password = "majulancar";
        String toEmail = emailTo;
 
//        System.out.println("start sender email");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
        };

        String message = "<table>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td><img src=\"https://menej.com/assets/images/menej_285e8e.png\"></td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td>\r\n" + 
		        		"				Finish your registratin</strong><br>\r\n" + 
		        		"				Below is code confirmation for finishing your registration<br/>\r\n" +
		        		"				insert the code and then click submit in page of registration<br>\r\n" +
		        		"			</td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td>\r\n" + 
		        		"				<div style=\"padding: 5px;border-radius: 3px;border: 1px solid #CCC;float: left;font-weight: bolder;font-size: 15px;\">"+codeConfirm+"</div>"+
		        		"			</td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"		<tr>\r\n" + 
		        		"			<td>\r\n" + 
		        		"				<div style=\"border-top: 1px solid #CCC;color: #a0a0a0;padding-top: 10px;font-size: 11px;margin-top: 10px;\">\r\n" + 
		        		"					This is email confirmation by menej for "+emailTo+"\r\n" + 
		        		"				</div>\r\n" + 
		        		"			</td>\r\n" + 
		        		"		</tr>\r\n" + 
		        		"	</table>";

        Session session = Session.getDefaultInstance(props, auth);
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendEmial(session, toEmail, "Regitration confirmation", message);
    }
}