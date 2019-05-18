package netgloo.models;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface BNPPFAccountDetailDao extends CrudRepository<BNPPFAccountDetail, Long> {

	@Query("SELECT u FROM BNPPFAccountDetail u WHERE u.accountNumber LIKE CONCAT('%',:accountNumber,'%')")
	BNPPFAccountDetail findAccount(@Param("accountNumber") String accountNumber);
	
}
