package org.gunitha.sitemanagementsystem.repository.user;

import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerUserRepository extends JpaRepository<DealerUser, Long> {

	DealerUser findByUsername(String username);
	
	
}
