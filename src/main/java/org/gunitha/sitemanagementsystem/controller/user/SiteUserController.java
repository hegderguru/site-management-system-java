package org.gunitha.sitemanagementsystem.controller.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.gunitha.sitemanagementsystem.model.user.SiteOwner;
import org.gunitha.sitemanagementsystem.service.user.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application/account/dealership/site/users/user")
@CrossOrigin("http://localhost:3000")
public class SiteUserController {

	@Autowired
	SiteUserService siteUserService;

	@PostMapping("/engineer/add")
	public ResponseEntity<SiteEngineer> registerSiteEngineer(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			SiteEngineer siteEngineer = siteUserService.registerOrUpdateSiteEngineer(userBean);
			return ResponseEntity.ok(siteEngineer);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/engineer/update")
	public ResponseEntity<SiteEngineer> updateSiteEngineer(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			SiteEngineer siteEngineer = siteUserService.registerOrUpdateSiteEngineer(userBean);
			return ResponseEntity.ok(siteEngineer);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/engineer/view/{idOrName}")
	public ResponseEntity<SiteEngineer> findSiteEngineerByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		SiteEngineer siteEngineer = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			siteEngineer = siteUserService.findSiteEngineerById(id);
		} catch (Exception e) {

		}
		if (null == id) {
			siteEngineer = siteUserService.findSiteEngineerByUsername(idOrName);
		}

		return ResponseEntity.ok().body(siteEngineer);
	}
	
	@PostMapping("/owner/add")
	public ResponseEntity<SiteOwner> registerSiteOwner(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			SiteOwner siteOwner = siteUserService.registerOrUpdateSiteOwner(userBean);
			return ResponseEntity.ok(siteOwner);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/owner/update")
	public ResponseEntity<SiteOwner> updateSiteOwner(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			SiteOwner siteOwner = siteUserService.registerOrUpdateSiteOwner(userBean);
			return ResponseEntity.ok(siteOwner);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/owner/view/{idOrName}")
	public ResponseEntity<SiteOwner> findSiteOwnerByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		SiteOwner siteOwner = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			siteOwner = siteUserService.findSiteOwnerById(id);
		} catch (Exception e) {

		}
		if (null == id) {
			siteOwner = siteUserService.findSiteOwnerByUsername(idOrName);
		}

		return ResponseEntity.ok().body(siteOwner);
	}
	
	@GetMapping("/engineer/users")
	public ResponseEntity<List<SiteEngineer>> siteEngineers() {
			List<SiteEngineer> siteEngineers = siteUserService.findAllSiteEngineers();
			return ResponseEntity.ok(siteEngineers);
	}
	
	@GetMapping("/owner/users")
	public ResponseEntity<List<SiteOwner>> siteOwners() {
			List<SiteOwner> siteOwners = siteUserService.findAllSiteOwners();
			return ResponseEntity.ok(siteOwners);
	}
	
	@GetMapping("/engineer/engineers/{startsWithUserName}")
	public ResponseEntity<List<SiteEngineer>> siteEngineers(@PathVariable String startsWithUserName) {
			List<SiteEngineer> siteEngineers = siteUserService.findAllSiteEngineersStartsWithUserName(startsWithUserName);
			return ResponseEntity.ok(siteEngineers);
	}
	
	@GetMapping("/owner/owners/{startsWithUserName}")
	public ResponseEntity<List<SiteOwner>> siteOwners(@PathVariable String startsWithUserName) {
			List<SiteOwner> siteOwners = siteUserService.findAllOwnersStartsWithUserName(startsWithUserName);
			return ResponseEntity.ok(siteOwners);
	}

}
