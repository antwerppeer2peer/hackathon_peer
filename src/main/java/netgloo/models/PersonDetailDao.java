package netgloo.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PersonDetailDao extends CrudRepository<PersonDetail, Long> {
	
	

}
