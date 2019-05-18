package netgloo.models;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface INGAccountDetailDao extends CrudRepository<INGAccountDetail, Long> {
	
	@Query("SELECT u FROM INGAccountDetail u WHERE u.accountNumber LIKE CONCAT('%',:accountNumber,'%')")
	INGAccountDetail findAccount(@Param("accountNumber") String accountNumber);

}
