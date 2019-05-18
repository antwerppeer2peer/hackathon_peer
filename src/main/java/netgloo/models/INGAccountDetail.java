package netgloo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ING_API_ACCOUNT_DETAIL")
public class INGAccountDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ING_ACCOUNT_ID")
	private long ingAccountID;
	
	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name="ACCOUNT_IBAN")
	private String accountIBAN;
	
	@Column(name="BALANCE")
	private Integer balance;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
	
	public long getIngAccountID() {
		return ingAccountID;
	}

	public void setIngAccountID(long ingAccountID) {
		this.ingAccountID = ingAccountID;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountIBAN() {
		return accountIBAN;
	}

	public void setAccountIBAN(String accountIBAN) {
		this.accountIBAN = accountIBAN;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
}
