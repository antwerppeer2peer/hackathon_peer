package netgloo.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * 
 * @author DhananJeyan
 *
 */
@Component
@PropertySource("classpath:config.properties")
public class EmaiHelper {
	
	public static final Logger log = LoggerFactory.getLogger(EmaiHelper.class);
	
	private Session session = null;
	
	private String from_mail;
	private String mime_type;
	
	/**
	 * 
	 * @param AUTH
	 * @param TLS
	 * @param SMTP
	 * @param PORT
	 * @param FROM_MAIL
	 * @param PASSWORD
	 */
	@Autowired
	public EmaiHelper(@Value("${mail.smtp.auth}") String AUTH,@Value("${mail.smtp.starttls.enable}") String TLS, @Value("${mail.smtp.host}") String SMTP,@Value("${mail.smtp.port}") String PORT,
			@Value("${mail.smtp.from}") String FROM_MAIL,@Value("${mail.smtp.password}") String PASSWORD,@Value("${mail.smtp.mime}") String MIME) {
	     log.info("--Loading Email Properties--");
	     
	     log.info("--Loading Email Properties Auth{}, TLS {}, SMTP {}, PORT {}, FROM {}, PASSWORD {}, MIME {}  --",AUTH,TLS,SMTP,PORT,FROM_MAIL,PASSWORD,MIME);
	    
	    Properties props = new Properties();
	    
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", TLS);
		props.put("mail.smtp.host", SMTP);
		props.put("mail.smtp.port", PORT);
		
		from_mail = FROM_MAIL;
		mime_type = MIME;

		session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(FROM_MAIL, PASSWORD);
		      }
		});
	}
	
	/**
	 * 
	 * @param emailInputData
	 */
	public boolean sendEmail(EmailInputData emailInputData) {
		log.info("---Sending Email---");
		
		boolean isOk = Boolean.TRUE;
		
		try {

			if(session != null) {
				log.info("--Sending Email TO {}---",emailInputData.getToEmail());
				
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(from_mail, false));

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInputData.getToEmail()));
				msg.setSubject(emailInputData.getSubject());
				msg.setContent(emailInputData.getContent(),mime_type);
				msg.setSentDate(new Date());
				
				Transport.send(msg);
				
			}
			
		}catch(Exception exception) {
			log.error(exception.getMessage());
			isOk = Boolean.FALSE;
			
		}
		
		return isOk;
	}
}
