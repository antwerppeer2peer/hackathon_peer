package netgloo.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "APP_PERSON_DETAILS")
public class PersonDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PERSON_ID")
	private long personID;
	
	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name="EMAIL_ID")
	private String emailID;
	
	@Column(name="ADDRESS")
	private String Address;
	
	@Column(name="NAME")
	private String name	;
	
	@Column(name="VALIDATED")
	private String validated;	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	
	@OneToMany(mappedBy = "personDetail")
	private List<AccountDetails> accountDetails = new ArrayList<AccountDetails>();
	
	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public List<AccountDetails> getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(List<AccountDetails> items) {
		this.accountDetails = accountDetails;
	}

	
}
