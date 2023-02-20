package org.gunitha.sitemanagementsystem.repository.user;

import org.gunitha.sitemanagementsystem.model.user.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
	AccountUser findByUsername(String username);
}