package netgloo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import netgloo.models.User;
import netgloo.models.UserDao;

@SpringBootApplication
@ComponentScan(basePackages = { "netgloo.*" })
public class Application  implements CommandLineRunner {
	
	@Autowired
	UserDao userDao;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

@Override
public void run(String... arg0) throws Exception {

	User user = new User();
	user.setEmail("23");
	user.setName("Dhana");
	userDao.save(user);
}

}
