package org.gunitha.sitemanagementsystem.controller.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.gunitha.sitemanagementsystem.service.role.RoleService;
import org.gunitha.sitemanagementsystem.service.user.DealerUserService;
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
@RequestMapping("/application/account/dealership/users/user")
@CrossOrigin("http://localhost:3000")
public class DealerUserController {

	@Autowired
	DealerUserService dealerUserService;

	@Autowired
	RoleService roleService;

	@PostMapping("/add")
	public ResponseEntity<DealerUser> register(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			DealerUser dealerUser = dealerUserService.transformToDealerUser(userBean);
			dealerUser = dealerUserService.register(dealerUser);
			return ResponseEntity.ok(dealerUser);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<DealerUser> update(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			DealerUser dealerUser = dealerUserService.transformToDealerUser(userBean);
			dealerUser = dealerUserService.register(dealerUser);
			return ResponseEntity.ok(dealerUser);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/view/{idOrName}")
	public ResponseEntity<DealerUser> findByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		DealerUser dealerUser = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			dealerUser = dealerUserService.findById(id);
		} catch (Exception e) {

		}
		if (null == id) {
			dealerUser = dealerUserService.findByUsername(idOrName);
		}

		return ResponseEntity.ok().body(dealerUser);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<DealerUser>> userss() {
			System.out.println("dealerships");
			List<DealerUser> dealerUsers = dealerUserService.findAll();
			return ResponseEntity.ok(dealerUsers);
	}

}
