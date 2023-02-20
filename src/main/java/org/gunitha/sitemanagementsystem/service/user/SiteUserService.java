package org.gunitha.sitemanagementsystem.service.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.gunitha.sitemanagementsystem.model.user.SiteOwner;

public interface SiteUserService {

	SiteOwner findSiteOwnerById(Long id);

	SiteOwner findSiteOwnerByUsername(String username);

	SiteOwner registerSiteOwner(SiteOwner siteOwner, Boolean admin, String privilege);

	SiteEngineer findSiteEngineerById(Long id);

	SiteEngineer findSiteEngineerByUsername(String username);

	SiteEngineer registerOrUpdateSiteEngineer(UserBean userBean);

	SiteOwner registerOrUpdateSiteOwner(UserBean userBean);

	List<SiteEngineer> findAllSiteEngineers();

	List<SiteOwner> findAllSiteOwners();

	List<SiteEngineer> findAllSiteEngineersStartsWithUserName(String startsWithUserName);

	List<SiteOwner> findAllOwnersStartsWithUserName(String startsWithUserName);

}
