package netgloo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import netgloo.convertor.DTOToDatabaseConvertor;
import netgloo.dto.TransactionDetailDTO;
import netgloo.email.EmaiHelper;
import netgloo.email.EmailInputData;
import netgloo.models.BNPPFAccountDetail;
import netgloo.models.BNPPFAccountDetailDao;
import netgloo.models.INGAccountDetail;
import netgloo.models.INGAccountDetailDao;
import netgloo.models.PersonDetail;
import netgloo.models.PersonDetailDao;
import netgloo.models.TransactionDetails;
import netgloo.models.TransactionDetailsDao;
import netgloo.sms.SMSHelper;

@Component
public class TransactionService {
	
	public static final Logger log = LoggerFactory.getLogger(RegisterService.class);
	
	@Autowired
	PersonDetailDao personDetailDao;
	
	@Autowired
	TransactionDetailsDao transactionDetailsDao;
	
	@Autowired
	INGAPIServiceProvider ingapiServiceProvider;
	
	@Autowired
	BNPPFAPIServiceProvider bnppfapiServiceProvider;

	@Autowired
	INGAccountDetailDao ingAccountDetailDao;
	
	@Autowired
	BNPPFAccountDetailDao bnppfAccountDetailDao;
	
	@Autowired
	SMSHelper smsHelper;
	
	@Autowired
	EmaiHelper emaiHelper;
	
	
	/**
	 * 
	 * @param transactionDetailDTO
	 * @return
	 */
	public String send(TransactionDetailDTO transactionDetailDTO) {
		log.info("-----Inside Send-----");
		
		String resonseText = "";
		
		PersonDetail creditorPerson = personDetailDao.findByMobileNumber(transactionDetailDTO.getCreditorMobileNumber());
		log.info("-----Creditor Person {} ----",creditorPerson.getPersonID());
		
		PersonDetail debitorPerson = personDetailDao.findByMobileNumber(transactionDetailDTO.getDebtorMobileNumber());
		log.info("-----Debitor Person {} -----",debitorPerson.getPersonID());
		
		ExternalAPIInputData externalAPIInputData = new ExternalAPIInputData();
		externalAPIInputData.setMobileNumber(transactionDetailDTO.getDebtorMobileNumber());
		externalAPIInputData.setAccountNumber(debitorPerson.getAccountDetails().iterator().next().getAccountNumber());
		Integer debitorBalance = getBalance(externalAPIInputData);
		log.info("-----Found Debitor Balance {} -----",debitorBalance);
		
		
		if(debitorBalance >= transactionDetailDTO.getTransactionAmount()) {
			log.info("---Transaction Amount {} ",transactionDetailDTO.getTransactionAmount());
			
			TransactionDetails transactionDetails = DTOToDatabaseConvertor.transactionDetails(transactionDetailDTO);
			transactionDetails.setStatus("100");
			transactionDetailsDao.save(transactionDetails);
			
			boolean isDebitOk = handleDebit(debitorPerson,transactionDetailDTO);
			
			boolean isCreditOk = handleCredit(creditorPerson,transactionDetailDTO);
			
			handleSmsAndMail(transactionDetailDTO, debitorPerson,creditorPerson,isDebitOk,isCreditOk);

			resonseText= "OK";
			
			return resonseText;
		}else {
			return "Debitor Balance is LOW";
		}
	}

	/**
	 * 
	 * @param creditorPerson
	 * @param transactionDetailDTO
	 * @return
	 */
	private boolean handleCredit(PersonDetail creditorPerson, TransactionDetailDTO transactionDetailDTO) {
		
		boolean isTransactionCreditOk = Boolean.FALSE;
		
		switch(creditorPerson.getAccountDetails().iterator().next().getBankName()) {
		
		case "ING":
			INGAccountDetail ingAccountDetail = ingAccountDetailDao.findAccount(creditorPerson.getAccountDetails().iterator().next().getAccountNumber());
			log.info("-----Account Number Found {} ---",ingAccountDetail.getAccountNumber());
			
			ingAccountDetail.setBalance(ingAccountDetail.getBalance()+transactionDetailDTO.getTransactionAmount());
			ingAccountDetailDao.save(ingAccountDetail);
			isTransactionCreditOk = Boolean.TRUE;
			break;
		case "BNPPF":
			BNPPFAccountDetail bnppfAccountDetail = bnppfAccountDetailDao.findAccount(creditorPerson.getAccountDetails().iterator().next().getAccountNumber());
			log.info("-----Account Number Found {} ---",bnppfAccountDetail.getAccountNumber());
			
			bnppfAccountDetail.setBalance(bnppfAccountDetail.getBalance()+transactionDetailDTO.getTransactionAmount());
			bnppfAccountDetailDao.save(bnppfAccountDetail);
			isTransactionCreditOk = Boolean.TRUE;
			break;
		}
		
		return isTransactionCreditOk;
	}

	/**
	 * 
	 * @param debitorPerson
	 * @param transactionDetailDTO
	 * @return
	 */
	private boolean handleDebit(PersonDetail debitorPerson, TransactionDetailDTO transactionDetailDTO) {
		
		boolean isTransactionDebitOk = Boolean.FALSE;
		
		switch(debitorPerson.getAccountDetails().iterator().next().getBankName()) {
		
		case "ING":
			INGAccountDetail ingAccountDetail = ingAccountDetailDao.findAccount(debitorPerson.getAccountDetails().iterator().next().getAccountNumber());
			log.info("-----Account Number Found {} ---",ingAccountDetail.getAccountNumber());
			ingAccountDetail.setBalance(ingAccountDetail.getBalance() - transactionDetailDTO.getTransactionAmount());
			ingAccountDetailDao.save(ingAccountDetail);
			isTransactionDebitOk = Boolean.TRUE;
			break;
		case "BNPPF":
			BNPPFAccountDetail bnppfAccountDetail = bnppfAccountDetailDao.findAccount(debitorPerson.getAccountDetails().iterator().next().getAccountNumber());
			log.info("-----Account Number Found {} ---",bnppfAccountDetail.getAccountNumber());
			bnppfAccountDetail.setBalance(bnppfAccountDetail.getBalance() - transactionDetailDTO.getTransactionAmount());
			bnppfAccountDetailDao.save(bnppfAccountDetail);
			isTransactionDebitOk = Boolean.TRUE;
			break;
		}
		
		return isTransactionDebitOk;
	}

	/**
	 * 
	 * @param transactionDetailDTO
	 * @param debitorPerson
	 * @param creditorPerson
	 */
	private void handleSmsAndMail(TransactionDetailDTO transactionDetailDTO, PersonDetail debitorPerson, PersonDetail creditorPerson,boolean isDebitOk,boolean isCreditOk) {
		
		if(isDebitOk) {
			

			smsHelper.send("Your Account is Debited with "+transactionDetailDTO.getTransactionAmount(), transactionDetailDTO.getDebtorMobileNumber());
			
			EmailInputData emailInputData = new EmailInputData();
			emailInputData.setContent("Your Account is Debited with "+transactionDetailDTO.getTransactionAmount());
			emailInputData.setSubject("Transaction Details PeerPay");
			emailInputData.setToEmail(debitorPerson.getEmailID());
			emaiHelper.sendEmail(emailInputData);
		}
		
		if(isCreditOk) {
			
			smsHelper.send("Your Account is Credited with "+transactionDetailDTO.getTransactionAmount(), transactionDetailDTO.getCreditorMobileNumber());
			
			EmailInputData emailInputData2 = new EmailInputData();
			emailInputData2.setContent("Your Account is Credited with "+transactionDetailDTO.getTransactionAmount());
			emailInputData2.setSubject("Transaction Details PeerPay");
			emailInputData2.setToEmail(creditorPerson.getEmailID());
			emaiHelper.sendEmail(emailInputData2);
		}
	}


	/**
	 * getBalance 
	 * 
	 * @param externalAPIInputData
	 * @return
	 */
	private Integer getBalance(ExternalAPIInputData externalAPIInputData) {
		log.info("-----Inside GetBalance---");
		
		Integer bankBalance = 0;
		
		PersonDetail personDetail = personDetailDao.findByMobileNumber(externalAPIInputData.getMobileNumber());
		log.info("-----Person {} --",personDetail.getPersonID());
		
		if(personDetail != null) {
			
			switch(personDetail.getAccountDetails().iterator().next().getBankName()) {
			
			case "ING":
				INGAccountDetail ingAccountDetail = ingAccountDetailDao.findAccount(externalAPIInputData.getAccountNumber());
				log.info("-----Account Number Found {} ---",ingAccountDetail.getAccountNumber());
				return ingAccountDetail.getBalance();
			case "BNPPF":
				BNPPFAccountDetail bnppfAccountDetail = bnppfAccountDetailDao.findAccount(externalAPIInputData.getAccountNumber());
				log.info("-----Account Number Found {} ---",bnppfAccountDetail.getAccountNumber());
				return bnppfAccountDetail.getBalance();
			}
		}
		
		return bankBalance;
	}
	
	
}
