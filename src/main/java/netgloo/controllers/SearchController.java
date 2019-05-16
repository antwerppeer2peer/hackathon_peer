package netgloo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.dto.PersonDTO;
import netgloo.service.impl.SearchService;

@Component
@RequestMapping("/search")
public class SearchController {
	
	public static final Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	SearchService searchService;
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	@RequestMapping("/searchWithMobile")
	@ResponseBody
	public List<PersonDTO> search(@RequestParam String mobileNumber) {
		log.info("---Search---");
		
		List<PersonDTO> personDTO = new ArrayList<PersonDTO>();
		personDTO.addAll(searchService.search("+"+mobileNumber));
		return personDTO;
	}
}
