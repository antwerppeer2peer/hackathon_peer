package netgloo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import netgloo.convertor.DTOToDatabaseConvertor;
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
			
			TransactionDetails transactionDetails = DTOToDatabaseConvertor.transactionDetails(transactionDetailDTO);
			transactionDetails.setStatus("100");
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
