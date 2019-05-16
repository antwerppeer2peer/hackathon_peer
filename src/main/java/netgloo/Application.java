package netgloo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import netgloo.dto.AccountDetailDTO;
import netgloo.dto.PersonDTO;
import netgloo.dto.TransactionDetailDTO;

@SpringBootApplication
@ComponentScan(basePackages = { "netgloo.*" })
public class Application{
	
  public static void main(String[] args) {
	  
    SpringApplication.run(Application.class, args);
    
    
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
    transactionDetailDTO.setCreditorAccount("BE78945");
    transactionDetailDTO.setDebtorAccount("BE78945");
    transactionDetailDTO.setTransactionAmount(78);
    
    String transactionDetailDTOjson = gson.toJson(transactionDetailDTO);// obj is your object 
    
    System.out.println(transactionDetailDTOjson);
  }
}
