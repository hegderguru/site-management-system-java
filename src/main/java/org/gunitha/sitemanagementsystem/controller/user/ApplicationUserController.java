package org.gunitha.sitemanagementsystem.controller.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.user.ApplicationUser;
import org.gunitha.sitemanagementsystem.service.user.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/application/users/user",})
public class ApplicationUserController {

	@Autowired
	ApplicationUserService applicationUserService;

	@PostMapping("/add")
	public ResponseEntity<ApplicationUser> register(@RequestBody UserBean userBean) {		
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {			
			ApplicationUser applicationUser = applicationUserService.transformToApplicationUser(userBean);			
			applicationUser = applicationUserService.register(applicationUser);
			return ResponseEntity.ok(applicationUser);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<ApplicationUser> update(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			ApplicationUser applicationUser = applicationUserService.transformToApplicationUser(userBean);
			applicationUser = applicationUserService.update(applicationUser);
			return ResponseEntity.ok(applicationUser);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/view/{idOrName}")
	public ResponseEntity<ApplicationUser> findByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		ApplicationUser applicationUser = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			applicationUser = applicationUserService.findById(id);
		} catch (Exception e) {

		}
		if (null == id) {
			applicationUser = applicationUserService.findByUsername(idOrName);
		}

		return ResponseEntity.ok().body(applicationUser);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<ApplicationUser>> findAll() {
		List<ApplicationUser> findAll = applicationUserService.findAll();		
		return ResponseEntity.ok(findAll);
	}
}
