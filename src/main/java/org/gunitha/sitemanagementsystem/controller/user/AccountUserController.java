package org.gunitha.sitemanagementsystem.controller.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.user.AccountUser;
import org.gunitha.sitemanagementsystem.service.user.AccountUserService;
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
@RequestMapping(path = {"/application/account/users/user"})
@CrossOrigin("http://localhost:3000")
public class AccountUserController {

	@Autowired
	AccountUserService accountUserService;
	
	@PostMapping("/add")
	public ResponseEntity<AccountUser> register(@RequestBody UserBean userBean) {		
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {			
			AccountUser accountUser = accountUserService.register(userBean);
			return ResponseEntity.ok(accountUser);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<AccountUser> update(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			AccountUser accountUser = accountUserService.register(userBean);
			return ResponseEntity.ok(accountUser);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/view/{idOrName}")
	public ResponseEntity<AccountUser> findByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		AccountUser accountUser = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			accountUser = accountUserService.findById(id);
		} catch (Exception e) {

		}
		if (null == id) {
			accountUser = accountUserService.findByUsername(idOrName);
		}

		return ResponseEntity.ok().body(accountUser);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<AccountUser>> findAll() {
		List<AccountUser> findAll = accountUserService.findAll();		
		return ResponseEntity.ok(findAll);
	}

}
