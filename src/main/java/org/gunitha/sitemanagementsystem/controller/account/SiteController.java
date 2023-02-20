package org.gunitha.sitemanagementsystem.controller.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.SiteBean;
import org.gunitha.sitemanagementsystem.model.account.Site;
import org.gunitha.sitemanagementsystem.service.account.AccountService;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.gunitha.sitemanagementsystem.service.account.DealersipService;
import org.gunitha.sitemanagementsystem.service.account.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms/application/account/dealership/site")
@CrossOrigin("http://localhost:3000")
public class SiteController {

	@Autowired
	SiteService siteService;

	@Autowired
	DealersipService dealersipService;

	@Autowired
	AccountService accountService;

	@Autowired
	ApplicationService applicationService;

	@PostMapping("/add")
	public ResponseEntity<Site> register(@RequestBody SiteBean siteBean) {
		Site site = siteService.register(siteBean);
		return ResponseEntity.ok().body(site);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Site> updates(@RequestBody SiteBean siteBean) {
		Site site = siteService.updates(siteBean);
		return ResponseEntity.ok().body(site);
	}

	@GetMapping("/view/{idOrName}")
	public ResponseEntity<Site> findByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		Site site = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			site = siteService.findById(id);
		} catch (Exception e) {
			
		}
		if (null == id) {
			site = siteService.findByName(idOrName);
		}

		return ResponseEntity.ok().body(site);
	}

	@GetMapping("/sites")
	public ResponseEntity<List<Site>> findAll() {
		return ResponseEntity.ok().body(siteService.findAll());
	}
	
	/*
	 * public List<Dealership> userDealerships(@PathVariable Long userId){ retun
	 * siteService.userDealerships(userId); }
	 */

}
