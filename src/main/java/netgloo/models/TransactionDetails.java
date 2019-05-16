package netgloo.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_TRANSACTION_DETAILS")
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TRANSACTION_ID")
	private long transactionID;
	
	@Column(name="DEBTOR_ACCNT")
	private String debtorAccount;
	
	@Column(name="CREDITOR_ACCNT")
	private String creditorAccount;
	
	@Column(name="DEBTOR_PERSON_ID")
	private long debtorPersonID;
	
	@Column(name="CREDITOR_PERSON_ID")
	private long creditorPersonID;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;
	
	@Column(name="TRANSACTION_AMOUNT")
	private Integer transactionAmount;
	
	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public String getDebtorAccount() {
		return debtorAccount;
	}

	public void setDebtorAccount(String debtorAccount) {
		this.debtorAccount = debtorAccount;
	}

	public String getCreditorAccount() {
		return creditorAccount;
	}

	public void setCreditorAccount(String creditorAccount) {
		this.creditorAccount = creditorAccount;
	}

	public long getDebtorPersonID() {
		return debtorPersonID;
	}

	public void setDebtorPersonID(long debtorPersonID) {
		this.debtorPersonID = debtorPersonID;
	}

	public long getCreditorPersonID() {
		return creditorPersonID;
	}

	public void setCreditorPersonID(long creditorPersonID) {
		this.creditorPersonID = creditorPersonID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Integer transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	
}
