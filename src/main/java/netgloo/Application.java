package netgloo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import netgloo.controllers.LoginController;
import netgloo.dto.PersonDTO;
import netgloo.models.UserDao;

@SpringBootApplication
@ComponentScan(basePackages = { "netgloo.*" })
public class Application  implements CommandLineRunner {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	LoginController loginController;
	

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

@Override
public void run(String... arg0) throws Exception {
	
	PersonDTO personDTO = new PersonDTO();
	personDTO.setEmailID("kkdhananjeyan@gmail.com");
	personDTO.setAddress("Rue Saint Emily Jacqmain");
	personDTO.setMobileNumber("+32465270695");
	
	String text = loginController.create(personDTO);
	System.out.println(text);
	
	

}

}
