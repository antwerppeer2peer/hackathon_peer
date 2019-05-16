package netgloo.dto;

public class TransactionDetailDTO {
	
	private Integer transactionID;
	
	private String debtorAccount;
	
	private String creditorAccount;
	
	private long debtorPersonID;
	
	private long creditorPersonID;
	
	private String status;
	
	private String transactionDate;
	
	private Integer transactionAmount;
	
	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
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

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Integer transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	
}
