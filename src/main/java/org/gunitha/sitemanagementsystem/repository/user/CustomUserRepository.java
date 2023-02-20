package org.gunitha.sitemanagementsystem.repository.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.gunitha.sitemanagementsystem.model.user.SiteOwner;

public interface CustomUserRepository {

	List<SiteEngineer> findAllSiteEngineersStartsWithUserName(String startsWithUserName,String username);

	List<SiteOwner> findAllSiteOwnersStartsWithUserName(String startsWithUserName, String userName);

}
