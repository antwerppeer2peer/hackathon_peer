package netgloo.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



/**
 * 
 * <p>SMSHanlder -> Sending Message </p>
 * 
 * @author DhananJeyan
 *
 */
@Component
@PropertySource("classpath:config.properties")
public class SMSHelper {
	
	public static final Logger log = LoggerFactory.getLogger(SMSHelper.class);

	private String ACCOUNT_SID;
	
	private String AUTH_TOKEN;
	
	private String fromPhoneNumber;
	
	@Autowired
	public SMSHelper(@Value("${twilio.account_sid}") String ACCOUNT_SID,@Value("${twilio.token}") String AUTH_TOKEN, @Value("${twilio.phone_number}") String fromPhoneNumber) {
	     this.ACCOUNT_SID = ACCOUNT_SID;
	     this.AUTH_TOKEN=AUTH_TOKEN;
	     this.fromPhoneNumber = fromPhoneNumber;
	     log.info("---Property loaded AccoundId {}, Auth Token {}, fromPhone {}",ACCOUNT_SID,AUTH_TOKEN,fromPhoneNumber);
	}
    
	/**
	 * <p>send - Sending the text to Client</p>
	 * 
	 * @param smsBody
	 * @param toPhoneNumber
	 */
    public void send(String smsBody, String toPhoneNumber) {
    	log.info("------Sending SMS {} to {} --------",smsBody,toPhoneNumber);
    	
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(toPhoneNumber), // to
                        new PhoneNumber(fromPhoneNumber), // from
                        smsBody).create();
        
        log.info("-----Message Sent Status {} ------", message.getStatus());
    }
}
