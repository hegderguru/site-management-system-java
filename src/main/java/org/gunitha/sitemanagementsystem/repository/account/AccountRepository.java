package org.gunitha.sitemanagementsystem.repository.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	 List<Account> findByName(String name);
}
