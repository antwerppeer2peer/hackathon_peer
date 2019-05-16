package netgloo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.dto.TransactionDetailDTO;
import netgloo.service.impl.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	public static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(@RequestBody TransactionDetailDTO transactionDetailDTO) {
		log.info("----send--- ");
		
		try {
			transactionService.send(transactionDetailDTO);
		}catch(Exception e) {
			return "NOK";
		}
			
		return "OK";
	}
	

}
