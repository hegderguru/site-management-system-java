package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.SiteBean;
import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.model.account.Site;

public interface SiteService {

	public Site register(Site site);

	public Site findByName(String name);

	Site findById(Long id);

	Site findByNumber(String number);

	public Site register(SiteBean siteBean);

	public List<Site> findAll();

	public Site updates(SiteBean siteBean);

//	public List<Dealership> userDealerships(Long userId);
}
