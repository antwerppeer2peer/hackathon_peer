package netgloo.models;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PersonDetailDao extends CrudRepository<PersonDetail, Long> {
	
	 public PersonDetail findByMobileNumber(String mobileNumber);
	 
	 @Query("SELECT u FROM PersonDetail u WHERE u.mobileNumber LIKE CONCAT('%',:mobileNumber,'%')")
	 List<PersonDetail> findPerson(@Param("mobileNumber") String mobileNumber);
}
