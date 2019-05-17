package netgloo.convertor;

import java.sql.Date;

import netgloo.dto.PersonDTO;
import netgloo.dto.TransactionDetailDTO;
import netgloo.models.AccountDetails;
import netgloo.models.PersonDetail;
import netgloo.models.TransactionDetails;

public class DTOToDatabaseConvertor {
	
	public static TransactionDetails transactionDetails(TransactionDetailDTO transactionDetailDTO) {
		
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setCreditorAccount(transactionDetailDTO.getCreditorAccount());
		transactionDetails.setDebtorAccount(transactionDetailDTO.getDebtorAccount());
		transactionDetails.setCreditorPersonID(transactionDetailDTO.getCreditorPersonID());
		transactionDetails.setDebtorPersonID(transactionDetailDTO.getDebtorPersonID());
		transactionDetails.setTransactionAmount(transactionDetailDTO.getTransactionAmount());
		transactionDetails.setTransactionDate(new Date(System.currentTimeMillis()));
		return transactionDetails;
	}

	public static AccountDetails accountDetail(PersonDTO personDTO) {
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setAccountNumber(personDTO.getAccountDetailDTO().getAccountNumber());
		accountDetails.setBankName(personDTO.getAccountDetailDTO().getBankName());
		accountDetails.setIban(personDTO.getAccountDetailDTO().getIban());
		return accountDetails;
	}

	public static PersonDetail personDetail(PersonDTO personDTO) {
		PersonDetail personDetail = new PersonDetail();
		personDetail.setEmailID(personDTO.getEmailID());
		personDetail.setAddress(personDTO.getAddress());
		personDetail.setMobileNumber(personDTO.getMobileNumber());
		return personDetail;
	}
}
