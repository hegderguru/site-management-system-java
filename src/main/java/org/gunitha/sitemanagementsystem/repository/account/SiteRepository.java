package org.gunitha.sitemanagementsystem.repository.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.account.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

	List<Site> findByName(String name);

	List<Site> findByNumber(String number);

}
