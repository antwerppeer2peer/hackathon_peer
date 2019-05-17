package netgloo.service.impl;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;

import netgloo.convertor.DTOToDatabaseConvertor;
import netgloo.dto.AccountDetailDTO;
import netgloo.dto.LoginResponseDTO;
import netgloo.dto.PersonDTO;
import netgloo.email.EmaiHelper;
import netgloo.email.EmailInputData;
import netgloo.models.AccountDetails;
import netgloo.models.AccountDetailsDao;
import netgloo.models.PersonDetail;
import netgloo.models.PersonDetailDao;
import netgloo.sms.OTPHelper;
import netgloo.sms.SMSHelper;

@Component
public class RegisterService {
	
	public static final Logger log = LoggerFactory.getLogger(RegisterService.class);
	
	@Autowired
	PersonDetailDao personDetailDao;
	
	@Autowired
	AccountDetailsDao accountDetailsDao;
	
	@Autowired
	OTPHelper otpHelper;
	
	@Autowired
	SMSHelper smsHelper;
	
	@Autowired
	EmaiHelper emaiHelper;
	
	/**
	 * 
	 * @param personDTO
	 */
	public PersonDTO register(PersonDTO personDTO) {
		
		PersonDTO responsePersonDTO = new PersonDTO();
		
		Iterable<PersonDetail> presonDetailList = personDetailDao.findAll();
		PersonDetail personDetailExit = getPerson(presonDetailList,personDTO.getMobileNumber());
		log.info("---Person Detail Data {} ",personDetailExit);
		
		if(personDTO != null && personDetailExit == null) {
			
			PersonDetail personDetail = DTOToDatabaseConvertor.personDetail(personDTO);
			personDetail.setValidated("N");
			personDetail = personDetailDao.save(personDetail);
			
			AccountDetails accountDetails = DTOToDatabaseConvertor.accountDetail(personDTO);
			accountDetails.setPersonDetail(personDetail);
			accountDetails = accountDetailsDao.save(accountDetails);
			
			log.info("----------Person Detail Created {} --------------",personDetail.getPersonID());
			
			Integer otp = otpHelper.generateOTP(String.valueOf(personDetail.getPersonID()));
			//smsHelper.send("Hey, Antwerp P2P Welcomes, Your OTP is :"+String.valueOf(otp), personDTO.getMobileNumber());
			
			EmailInputData emailInputData = new EmailInputData();
			emailInputData.setToEmail(personDTO.getEmailID());
			emailInputData.setSubject("Welcome for P2P App - OTP ");
			emailInputData.setContent("Hello,  Your OTP !!!"+otp);
			emaiHelper.sendEmail(emailInputData);
			
			log.info("---OTP {} ",otp);
			
			responsePersonDTO.setPersonID(personDetail.getPersonID());
		}else if(personDetailExit != null) {
			
			responsePersonDTO.setPersonID(personDetailExit.getPersonID());
			responsePersonDTO.setStatus("NOK!!!, Already Registered");
		}
		
		return responsePersonDTO;
	}

	/**
	 * 
	 * @param personDTO
	 * @throws ExecutionException
	 */
	public void validate(PersonDTO personDTO) throws ExecutionException {
		
		if(personDTO != null) {
			log.info("----Person ID---{}",personDTO.getPersonID());
			
			if(personDTO.getOtp() != 0 && otpHelper.validateOTP(String.valueOf(personDTO.getPersonID()), personDTO.getOtp())) {
				
				PersonDetail personDetail = personDetailDao.findOne(personDTO.getPersonID());
				personDetail.setValidated("Y");
				personDetail = personDetailDao.save(personDetail);
			}
		}
	}
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public LoginResponseDTO login(String mobileNumber) {
		
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		
		if(!StringUtils.isNullOrEmpty(mobileNumber)) {
			log.info("--login mobile {} -",mobileNumber);
			
			Iterable<PersonDetail> presonDetailList = personDetailDao.findAll();
			PersonDetail personDetail = getPerson(presonDetailList,mobileNumber);
			log.info("---Person {} ",personDetail);
			
			
			if(personDetail != null && personDetail.getValidated().equalsIgnoreCase("Y")) {
				log.info("Found login {}", personDetail.getPersonID());
				
				PersonDTO personDTO = new PersonDTO();
				personDTO.setPersonID(personDetail.getPersonID());
				personDTO.setMobileNumber(personDetail.getMobileNumber());
				personDTO.setStatus("Ok");
				
				AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
				accountDetailDTO.setAccountNumber(personDetail.getAccountDetails().iterator().next().getAccountNumber());
				accountDetailDTO.setIban(personDetail.getAccountDetails().iterator().next().getIban());
				personDTO.setAccountDetailDTO(accountDetailDTO);
				
				loginResponseDTO.setPersonDTO(personDTO);
			}else {
				PersonDTO personDTO = new PersonDTO();
				personDTO.setPersonID(personDetail.getPersonID());
				personDTO.setStatus("NOK, Mobile Number Not Validated");
				
				loginResponseDTO.setPersonDTO(personDTO);
			}
		}
		
		return loginResponseDTO;
	}

	private PersonDetail getPerson(Iterable<PersonDetail> presonDetailList, String mobileNumber) {
		log.info("----Mobile Number {} ",mobileNumber);
		
		for(PersonDetail personDetail : presonDetailList) {
			log.info("-----PersonDetail Mobile Number {} ",personDetail.getMobileNumber());
			
			if(mobileNumber.equalsIgnoreCase(personDetail.getMobileNumber())) {
				log.info("---Matching Number--");
				return personDetail;
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}

}
