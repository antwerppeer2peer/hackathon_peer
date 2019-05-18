package netgloo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import netgloo.dto.PersonDTO;
import netgloo.models.PersonDetail;
import netgloo.models.PersonDetailDao;

@Component
public class SearchService {
	
	public static final Logger log = LoggerFactory.getLogger(SearchService.class);
	
	@Autowired
	PersonDetailDao personDetailDao;
	
	public List<PersonDTO> search(String mobileNumber){
		log.info("-------Search Service----");
		
		List<PersonDTO> listOfPersonDTO = new ArrayList<PersonDTO>();
		List<PersonDetail> listOfPersonDetail = personDetailDao.findPerson(mobileNumber);
		log.info("----Found Person Detail {} ",listOfPersonDetail);
		
		for(PersonDetail personDetail : listOfPersonDetail) {
			
			PersonDTO personDTO = new PersonDTO();
			personDTO.setPersonID(personDetail.getPersonID());
			personDTO.setMobileNumber(personDetail.getMobileNumber());
			personDTO.setName(personDetail.getName());
			personDTO.setStatus("Ok");
			listOfPersonDTO.add(personDTO);
		}
		return listOfPersonDTO;
	}
}
