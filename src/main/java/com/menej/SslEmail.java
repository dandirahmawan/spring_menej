package com.menej;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import java.util.Properties;

public class SslEmail {

	public Properties emailProperties(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

		return props;
	}

	public Authenticator authEmail(String emailTo){
		String fromEmail = "menejproyek@gmail.com";
		String password = "kanggebantudamelan100";
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		return auth;
	}

    public void sendEmail(String emailTo, String codeConfirm, String userName){
        Properties props = emailProperties();
        Authenticator auth = authEmail(emailTo);
		String toEmail = emailTo;
		String message = "<div style='background: #e1e1e1; padding: 40px'>\n" +
				"                <div style='max-width: 280px; padding: 10px; margin: auto; border: 1px solid #d8d8d8; border-radius: 5px; background: #FFF;'>\n" +
				"                    <table>\n" +
				"                        <tr> \n" +
				"                            <td><img src='https://scontent.fcgk4-4.fna.fbcdn.net/v/t1.0-9/119041998_3359216697526275_5140025698278560974_n.jpg?_nc_cat=101&_nc_sid=730e14&_nc_eui2=AeGb5dgJggtCxe8Gf7JhczaLZ-yXZaga0V5n7JdlqBrRXr4_7jq93tmJrFeJN79mAbmFC1qXoeXqwvFKSueo-xBW&_nc_ohc=I5ndHSrf8IYAX_mEL6u&_nc_ht=scontent.fcgk4-4.fna&oh=74b8358dc5aa99eb7df0b4f901459243&oe=5F7A057C'/></td>\n" +
				"                        </tr>\n" +
				"                        <tr> \n" +
				"                            <td>\n" +
				"                                <div style='font-size: 20px'><strong>Invitation</strong></div>\n" +
//				"                                <div style='color:#919191;margin-top: -5px;'>By : Dandi rahmawan</div>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                        <tr>\n" +
				"                            <td>\n" +
				"                                <strong style='font-size: 14px'>Hi, i am "+userName+"</strong><br/>\n" +
				"                                <div style='font-size: 11px'>\n" +
				"                                    I want invite you to colaborating in menej for project <strong>Menej development</strong><br/>\n" +
				"                                </div>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                        <tr>\n" +
				"                            <td> \n" +
				"                                <a href='http://localhost:3000/invitation?conf="+codeConfirm+"'>\n" +
				"                                    <button style='padding: 7px; border: none; background: #386384; color: #FFF; border-radius: 3px; font-size: 11px;margin-top: 10px'>\n" +
				"                                        Accept\n" +
				"                                    </button>\n" +
				"                                </a>\n" +
				"                            </td>\n" +
				"                        </tr> \n" +
				"                        <tr>\n" +
				"                            <td> \n" +
				"								 <div style='border-top: 1px solid #CCC; color: #a0a0a0; padding-top: 10px; font-size: 11px; margin-top: 10px'>" +
					"                                <div>\n" +
					"                                    This email will expired in 24 hours, click accept for the next step of approvement" +
					"								 </div>\n" +
				"								 </div>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                </div>\n" +
				"            </div>";

        Session session = Session.getDefaultInstance(props, auth);
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendEmial(session, toEmail, "Invitation", message);
    }
    
    public void sendEmailRegistration(String emailTo, String codeConfirm){
        Properties props = emailProperties();
        Authenticator auth = authEmail(emailTo);
        String toEmail = emailTo;

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

	public void sendEmailForgotPassword(String emailTo, String codeRecover){
		Properties props = emailProperties();
		Authenticator auth = authEmail(emailTo);
		String toEmail = emailTo;

		String message = "<table>\r\n" +
				"		<tr>\r\n" +
				"			<td><img src=\"https://menej.com/assets/images/menej_285e8e.png\"></td>\r\n" +
				"		</tr>\r\n" +
				"		<tr>\r\n" +
				"			<td>\r\n" +
				"				<strong>Forgot password</strong><br>\r\n" +
				"				You have request to reset your password, click request link below to reset your password<br/>\r\n" +
				" 				if you not send request to reset password, please igore this email<br>\r\n" +
				"			</td>\r\n" +
				"		</tr>\r\n" +
				"		<tr>\r\n" +
				"			<td>\r\n" +
				"		<a href=\"http://localhost:3000/account_recovery?r_code="+codeRecover+"\">" +
				"<button style=\"padding: 10px;border:none;background: #386384;color: #FFF;border-radius: 5px;\">Reset password</button></a>\r\n"+
				"			</td>\r\n" +
				"		</tr>\r\n" +
				"		<tr>\r\n" +
				"			<td>\r\n" +
				"				<div style=\"border-top: 1px solid #CCC;color: #a0a0a0;padding-top: 10px;font-size: 11px;margin-top: 10px;\">\r\n" +
				"					This is email accoun recpver by menej for "+emailTo+"\r\n" +
				"				</div>\r\n" +
				"			</td>\r\n" +
				"		</tr>\r\n" +
				"	</table>";

		Session session = Session.getDefaultInstance(props, auth);
		EmailUtil emailUtil = new EmailUtil();
		emailUtil.sendEmial(session, toEmail, "Forget password", message);
	}

	public void sendEmailChangeEmail(String emailTo, String codeConfirm, String userName){
		Properties props = emailProperties();
		Authenticator auth = authEmail(emailTo);
		String toEmail = emailTo;
		String message = "<div style='background: #e1e1e1; padding: 40px'>\n" +
				"                <div style='max-width: 280px; padding: 10px; margin: auto; border: 1px solid #d8d8d8; border-radius: 5px; background: #FFF;'>\n" +
				"                    <table>\n" +
				"                        <tr> \n" +
				"                            <td><img src='https://scontent.fcgk4-4.fna.fbcdn.net/v/t1.0-9/119041998_3359216697526275_5140025698278560974_n.jpg?_nc_cat=101&_nc_sid=730e14&_nc_eui2=AeGb5dgJggtCxe8Gf7JhczaLZ-yXZaga0V5n7JdlqBrRXr4_7jq93tmJrFeJN79mAbmFC1qXoeXqwvFKSueo-xBW&_nc_ohc=I5ndHSrf8IYAX_mEL6u&_nc_ht=scontent.fcgk4-4.fna&oh=74b8358dc5aa99eb7df0b4f901459243&oe=5F7A057C'/></td>\n" +
				"                        </tr>\n" +
				"                        <tr> \n" +
				"                            <td>\n" +
				"                                <div style='font-size: 20px'><strong>Change email request</strong></div>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                        <tr>\n" +
				"                            <td>\n" +
				"                                <strong style='font-size: 14px'>Hi "+userName+"</strong><br/>\n" +
				"                                <div style='font-size: 12px'>\n" +
				"									You have request to change your email account<br/>please click link below to confirm that your email is valid" +
				"                                </div>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                        <tr>\n" +
				"                            <td style='padding-top: 10px'> \n" +
				"                                <a style='font-size: 12px; font-weight: bold' href='http://localhost:3000/confirmation_email?conf="+codeConfirm+"'>\n" +
				"                                    http://localhost:3000/confirmation_email?conf="+codeConfirm+
				"                                </a>\n" +
				"                            </td>\n" +
				"                        </tr> \n" +
				"                        <tr>\n" +
				"                            <td> \n" +
				"								 <div style='border-top: 1px solid #CCC; color: #a0a0a0; padding-top: 10px; font-size: 11px; margin-top: 10px'>" +
				"                                <div>\n" +
				"                                    This link will expired in 24 hours, click link for change your email account" +
				"								 </div>\n" +
				"								 </div>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                </div>\n" +
				"            </div>";

		Session session = Session.getDefaultInstance(props, auth);
		EmailUtil emailUtil = new EmailUtil();
		emailUtil.sendEmial(session, toEmail, "Change email", message);
	}
}