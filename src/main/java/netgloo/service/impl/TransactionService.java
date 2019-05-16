package netgloo.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import netgloo.dto.TransactionDetailDTO;
import netgloo.models.PersonDetail;
import netgloo.models.PersonDetailDao;
import netgloo.models.TransactionDetails;
import netgloo.models.TransactionDetailsDao;

@Component
public class TransactionService {
	
	public static final Logger log = LoggerFactory.getLogger(RegisterService.class);
	
	@Autowired
	PersonDetailDao personDetailDao;
	
	@Autowired
	TransactionDetailsDao transactionDetailsDao;
	
	
	/**
	 * 
	 * @param transactionDetailDTO
	 * @return
	 */
	public String send(TransactionDetailDTO transactionDetailDTO) {
		log.info("-----Inside Send-----");
		
		String resonseText = "";
		
		if(isDetailsCorrect(transactionDetailDTO)) {
			
			TransactionDetails transactionDetails = new TransactionDetails();
			transactionDetails.setCreditorAccount(transactionDetailDTO.getCreditorAccount());
			transactionDetails.setDebtorAccount(transactionDetailDTO.getDebtorAccount());
			transactionDetails.setCreditorPersonID(transactionDetailDTO.getCreditorPersonID());
			transactionDetails.setDebtorPersonID(transactionDetailDTO.getDebtorPersonID());
			transactionDetails.setTransactionAmount(transactionDetailDTO.getTransactionAmount());
			transactionDetails.setStatus("100");
			transactionDetails.setTransactionDate(new Date(System.currentTimeMillis()));
			
			transactionDetailsDao.save(transactionDetails);
			resonseText= "OK";
		}
		return resonseText;
	}

	/**
	 * 
	 * @param transactionDetailDTO
	 * @return
	 */
	private boolean isDetailsCorrect(TransactionDetailDTO transactionDetailDTO) {
		
		Iterable<PersonDetail> personDetails = personDetailDao.findAll();
		
		List<Long> listOfPersonIDs = new ArrayList<Long>();
		List<String> listOfAccounts = new ArrayList<String>();
		
		for(PersonDetail personDetail : personDetails) {
			listOfPersonIDs.add(personDetail.getPersonID());
			listOfAccounts.add(personDetail.getAccountDetails().iterator().next().getAccountNumber());
		}
		
		if(!(listOfPersonIDs.contains(transactionDetailDTO.getCreditorPersonID()) && listOfPersonIDs.contains(transactionDetailDTO.getDebtorPersonID()))) {
			log.info("---Person ID incorrect-");
			return false;
		}
		
		if(!(listOfAccounts.contains(transactionDetailDTO.getCreditorAccount()) && listOfAccounts.contains(transactionDetailDTO.getDebtorAccount()))) {
			log.info("--Account number incorrect--");
			return false;
		}
		
		return true;
	}
}
