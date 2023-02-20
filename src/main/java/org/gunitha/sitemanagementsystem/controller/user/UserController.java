package org.gunitha.sitemanagementsystem.controller.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.user.User;
import org.gunitha.sitemanagementsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {			
			User user = userService.transformToAccount(userBean);			
			user = userService.registerUser(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> update(@RequestBody UserBean userBean) {
		if (StringUtils.hasText(userBean.getUsername()) && StringUtils.hasText(userBean.getPassword())) {
			User user = userService.transformToAccount(userBean);			
			user = userService.registerUser(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/find/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable String username) {

		if (StringUtils.hasText(username)) {
			User findByUsername = userService.findByUsername(username);
			return ResponseEntity.ok(findByUsername);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		List<User> findAll = userService.findAll();		
		return ResponseEntity.ok(findAll);
	}

}
