package org.gunitha.sitemanagementsystem.controller.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.AccountBean;
import org.gunitha.sitemanagementsystem.model.account.Account;
import org.gunitha.sitemanagementsystem.service.account.AccountService;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
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
@RequestMapping("/sms/application/account")
@CrossOrigin("http://localhost:3000")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	ApplicationService applicationService;

	@PostMapping("/add/{applicationId}")
	public ResponseEntity<Account> register(@PathVariable Long applicationId, @RequestBody AccountBean accountBean) {
		Account account = accountService.transformToAccount(accountBean, applicationId);
		account = accountService.register(account);
		return ResponseEntity.ok().body(account);
	}

	@GetMapping("/view/{idOrName}")
	public ResponseEntity<Account> findByIdOrName(@PathVariable String idOrName) {
		Long id = null;
		Account account = null;
		try {
			id = NumberUtils.parseNumber(idOrName, Long.class);
			account = accountService.findById(id);
		} catch (Exception e) {

		}
		if (null == id) {
			account = accountService.findByName(idOrName);
		}

		return ResponseEntity.ok().body(account);
	}

	@PostMapping("/update")
	public ResponseEntity<Account> register(@RequestBody AccountBean accountBean) {
		Account account = accountService.transformToAccount(accountBean);
		account = accountService.update(account);
		return ResponseEntity.ok().body(account);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> findAll() {
		return ResponseEntity.ok().body(accountService.findAll());
	}

}
