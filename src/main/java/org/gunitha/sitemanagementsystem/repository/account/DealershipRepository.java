package org.gunitha.sitemanagementsystem.repository.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository  extends JpaRepository<Dealership, Long>{

	List<Dealership> findByName(String name);
	
	List<Dealership> findByNumber(String number);

	@Query("select d from Dealership d where id in (:dealershipIds)")
	List<Dealership> findAllByIds(@Param("dealershipIds") List<Long> dealershipIds);
	
}
