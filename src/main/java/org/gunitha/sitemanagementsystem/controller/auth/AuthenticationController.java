package org.gunitha.sitemanagementsystem.controller.auth;

import org.gunitha.sitemanagementsystem.model.user.AccountUser;
import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.gunitha.sitemanagementsystem.model.user.User;
import org.gunitha.sitemanagementsystem.service.user.AccountUserService;
import org.gunitha.sitemanagementsystem.service.user.ApplicationUserService;
import org.gunitha.sitemanagementsystem.service.user.DealerUserService;
import org.gunitha.sitemanagementsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

	@Autowired
	UserService userService;

	@Autowired
	ApplicationUserService applicationUserService;
	
	@Autowired
	AccountUserService accountUserService;
	
	@Autowired
	DealerUserService dealerUserService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate() {

		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

			User findByUsername = userService
					.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			AuthenticatedUserBean authenticatedUserBean = new AuthenticatedUserBean(findByUsername);

			if (findByUsername instanceof AccountUser) {
				AccountUser accountUser = accountUserService.findById(findByUsername.getId());
				authenticatedUserBean.setAccountId(accountUser.getAccount().getId());
			}	
			if (findByUsername instanceof DealerUser) {
				DealerUser dealerUser = dealerUserService.findById(findByUsername.getId());
				authenticatedUserBean.setAccountId(dealerUser.getDealerships().get(0).getAccount().getId());
			}
			
			return ResponseEntity
					.ok()/*
							 * .header(HttpHeaders.AUTHORIZATION, Base64.getEncoder().encodeToString((new
							 * String("ghegde:ghegde")).getBytes()))
							 */
					.body(authenticatedUserBean);
		}

		return ResponseEntity.badRequest().body("Not Authenticated successfully!");
	}

}
