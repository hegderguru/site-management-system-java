package org.gunitha.sitemanagementsystem.repository.user;

import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteEngineerRepository extends JpaRepository<SiteEngineer, Long> {

	SiteEngineer findByUsername(String username);

}
