package netgloo.dto;

public class PersonDTO {
	
	private long personID;
	private String mobileNumber;
	private String emailID;
	private String Address;
	private Integer otp;
	private String name;
	private String status;
	private AccountDetailDTO accountDetailDTO;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public AccountDetailDTO getAccountDetailDTO() {
		return accountDetailDTO;
	}

	public void setAccountDetailDTO(AccountDetailDTO accountDetailDTO) {
		this.accountDetailDTO = accountDetailDTO;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	
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
}
