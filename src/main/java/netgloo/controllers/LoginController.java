package netgloo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.dto.PersonDTO;
import netgloo.models.PersonDetail;
import netgloo.models.PersonDetailDao;
import netgloo.sms.OTPHelper;
import netgloo.sms.SMSHelper;


/**
 * <p> Should be able to Registration and Login </p>
 * 
 * @author DhananJeyan
 *
 */
@Controller
public class LoginController {
	
	public static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	PersonDetailDao personDetailDao;
	
	@Autowired
	OTPHelper otpHelper;
	
	@Autowired
	SMSHelper smsHelper;
	
	@RequestMapping("/create")
	@ResponseBody
	public String create(PersonDTO personDTO) {
		
		Integer otp;
		
		try {
			
			if(personDTO != null) {
				
				PersonDetail personDetail = new PersonDetail();
				personDetail.setEmailID(personDTO.getEmailID());
				personDetail.setAddress(personDTO.getAddress());
				personDetail.setMobileNumber(personDTO.getMobileNumber());
				personDetail.setValidated("N");
				personDetail = personDetailDao.save(personDetail);
				
				log.info("----------Person Detail Created {} --------------",personDetail.getPersonID());
				
				otp = otpHelper.generateOTP(String.valueOf(personDetail.getPersonID()));
				
				smsHelper.send("Hey, Antwerp P2P Welcomes, Your OTP is :"+String.valueOf(otp), personDTO.getMobileNumber());
			}
			
		}catch(Exception e) {
			throw e;
		}
	
		return "Ok";
	}
	
	@RequestMapping("/validate")
	@ResponseBody
	public String validate(PersonDTO personDTO) {
		
		try {
			
			if(personDTO != null) {
				
				if(personDTO.getOtp() != 0 && otpHelper.validateOTP(String.valueOf(personDTO.getPersonID()), personDTO.getOtp())) {
					
					PersonDetail personDetail = personDetailDao.findOne(personDTO.getPersonID());
					personDetail.setValidated("Y");
					personDetail = personDetailDao.save(personDetail);
				}else {
					return "Please Verify your Nunmber";
				}
			}
			
		}catch(Exception e) {
			return "Error While Registation Process";
		}
	
		return "Registration Completed Successfully";
	}
}
