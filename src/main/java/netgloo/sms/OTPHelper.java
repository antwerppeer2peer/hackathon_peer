package netgloo.sms;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * <p> Generates OTP and Validate OTP </p>
 * 
 * @author DhananJeyan
 *
 */
@Component
@PropertySource("classpath:config.properties")
public class OTPHelper {
	
	public static final Logger log = LoggerFactory.getLogger(OTPHelper.class);
	
	
	private long EXPIRE_MIN;
	
	private LoadingCache<String, Integer> otpCache;
	
	@Autowired
	public OTPHelper(@Value("${cache.expire_min}") long EXPIRE_MIN) {
	     this.EXPIRE_MIN = EXPIRE_MIN;
	     System.out.println("================== " + EXPIRE_MIN + "================== ");
	     
	 	otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
       });
	}
	
	/**
	 * <p>generateOTP - Method Generates OTP </p>
	 * 
	 * @return
	 */
	public Integer generateOTP(String phoneNumber) {
		log.info("------Inside generateOTP {}----------",phoneNumber);
		log.info("------Inside generateOTP {}----------",otpCache);
	
		Random random = new Random();
	    int OTP = 100000 + random.nextInt(900000);
	    otpCache.put(phoneNumber, OTP);
	    
	    log.info("-----Key Generated for {} and OTP is {} ----------",phoneNumber, OTP);

	    return OTP;
	}
	
	/**<p>validateOTP - This method validates OTP </p>
	 * 
	 * @param phoneNumber
	 * @param OTP
	 * @return
	 * @throws ExecutionException 
	 */
	public boolean validateOTP(String phoneNumber,int OTP) throws ExecutionException {
		log.info("-----Inside validateOTP----");
		boolean isOTPValid = Boolean.TRUE;
		log.info("--------Found OTP in Cache {} -----------",otpCache.get(phoneNumber));
		
		if(otpCache.get(phoneNumber) ==  OTP) {
			isOTPValid = Boolean.FALSE;
			otpCache.invalidate(phoneNumber);
		}
		
		return isOTPValid;
	}

}
