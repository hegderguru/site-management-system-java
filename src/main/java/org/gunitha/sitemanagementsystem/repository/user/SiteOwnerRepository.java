package org.gunitha.sitemanagementsystem.repository.user;

import org.gunitha.sitemanagementsystem.model.user.SiteOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteOwnerRepository extends JpaRepository<SiteOwner, Long> {

	SiteOwner findByUsername(String username);

}
