package netgloo.dto;

import java.util.List;

public class PersonDTO {
	
	private long personID;
	private String mobileNumber;
	private String emailID;
	private String Address;
	private Integer otp;
	
	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	private List<AccountDetailDTO> accountDetailDTOs;
	
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

	public List<AccountDetailDTO> getAccountDetailDTOs() {
		return accountDetailDTOs;
	}

	public void setAccountDetailDTOs(List<AccountDetailDTO> accountDetailDTOs) {
		this.accountDetailDTOs = accountDetailDTOs;
	}
}
