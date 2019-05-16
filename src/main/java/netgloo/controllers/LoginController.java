package netgloo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.dto.LoginResponseDTO;
import netgloo.dto.PersonDTO;
import netgloo.service.impl.RegisterService;


/**
 * <p> Should be able to Registration and Login </p>
 * 
 * @author DhananJeyan
 *
 */
@Controller
@RequestMapping("/register")
public class LoginController {
	
	public static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	RegisterService registerService;
	
	@RequestMapping("/create")
	@ResponseBody
	public PersonDTO create(@RequestBody PersonDTO personDTO) {
		
		PersonDTO responsePersonDTO = null;
		
		try {
			responsePersonDTO = registerService.register(personDTO);
		}catch(Exception e) {
			responsePersonDTO = new PersonDTO();
			responsePersonDTO.setStatus("NOK");
		}
	
		return responsePersonDTO;
	}
	
	@RequestMapping("/validate")
	@ResponseBody
	public PersonDTO validate(@RequestBody PersonDTO personDTO) {
		
		PersonDTO validatePersonDTO = new PersonDTO();
		
		try {
			registerService.validate(personDTO);
		}catch(Exception e) {
			validatePersonDTO.setStatus("NOK");
		}
		
		validatePersonDTO.setStatus("Ok");
		return validatePersonDTO;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public LoginResponseDTO login(@RequestParam String mobileNumber) {
		log.info("----Login {} ",mobileNumber);
		
		LoginResponseDTO loginResponseDTO = null;
		
		try {
			loginResponseDTO = registerService.login("+"+mobileNumber);
		}catch(Exception e) {
			loginResponseDTO = new LoginResponseDTO();
			loginResponseDTO.setPersonDTO(new PersonDTO());
			loginResponseDTO.getPersonDTO().setStatus("NOK");
		}
			
		return loginResponseDTO;
	}
}
