package netgloo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import netgloo.dto.AccountDetailDTO;
import netgloo.dto.PersonDTO;
import netgloo.dto.TransactionDetailDTO;
import netgloo.service.impl.BNPPFAPIServiceProvider;
import netgloo.service.impl.INGAPIServiceProvider;

@SpringBootApplication
@ComponentScan(basePackages = { "netgloo.*" })
public class Application extends SpringBootServletInitializer{
	
	@Autowired
	INGAPIServiceProvider ingapiServiceProvider;
	
	@Autowired
	BNPPFAPIServiceProvider bnppfapiServiceProvider;
	
	
  public static void main(String[] args) {

	  SpringApplication.run(Application.class, args);
    
	  dummyJsonProvider();
  }
  
  @PostConstruct
  public void listen() { 
	// ingapiServiceProvider.addAccountDetails();
	// bnppfapiServiceProvider.addAccountDetails();
  }

  /**
   * 
   */
  @PostConstruct
private static void dummyJsonProvider() {
	
	PersonDTO person = new PersonDTO();
    person.setEmailID("kkdhananjeyan@gmail.com");
    person.setAddress("Rue Saint Laurent");
    person.setMobileNumber("+32465270695");
    person.setAccountDetailDTO(new AccountDetailDTO());
    person.getAccountDetailDTO().setBankName("BEO");
    person.getAccountDetailDTO().setAccountNumber("BE78945");
    person.getAccountDetailDTO().setIban("BE788");
    
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(person);// obj is your object 
    
    System.out.println(json);
    
    PersonDTO person1 = new PersonDTO();
    person1.setPersonID(234234);
    person1.setOtp(234234);
    
    String jso1n = gson.toJson(person1);// obj is your object 
    
    System.out.println(jso1n);
    
    TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO();
    transactionDetailDTO.setCreditorMobileNumber("BE78945");
    transactionDetailDTO.setDebtorMobileNumber("BE78945");
    transactionDetailDTO.setTransactionAmount(78);
    
    String transactionDetailDTOjson = gson.toJson(transactionDetailDTO);// obj is your object 
    
    System.out.println(transactionDetailDTOjson);
}
}
