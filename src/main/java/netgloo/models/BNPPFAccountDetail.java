package netgloo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="BNPPF_API_ACCOUNT_DETAIL")
public class BNPPFAccountDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="BNPPF_ACCOUNT_ID")
	private long bnppfAccountID;
	
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
	
	public long getBnppfAccountID() {
		return bnppfAccountID;
	}

	public void setBnppfAccountID(long bnppfAccountID) {
		this.bnppfAccountID = bnppfAccountID;
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

}
