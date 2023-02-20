package org.gunitha.sitemanagementsystem.repository.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.account.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	 List<Application> findByName(String name);
}
