package org.gunitha.sitemanagementsystem.controller.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.DealershipBean;
import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.service.account.AccountService;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.gunitha.sitemanagementsystem.service.account.DealersipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms/application/account/dealership")
@CrossOrigin("http://localhost:3000")
public class DealershipController {

	@Autowired
	DealersipService dealersipService;

	@Autowired
	AccountService accountService;

	@Autowired
	ApplicationService applicationService;

	@PostMapping("/add/{accountId}")
	public ResponseEntity<Dealership> registerApplication(@PathVariable Long accountId,
			@RequestBody DealershipBean dealershipBean) {
		Dealership dealership = dealersipService.register(dealershipBean,accountId);
		return ResponseEntity.ok().body(dealership);
	}

	@PostMapping("/update")
	public ResponseEntity<Dealership> update(@RequestBody DealershipBean dealershipBean) {
		Dealership dealership = dealersipService.transformToAccount(dealershipBean);
		dealership = dealersipService.update(dealership);
		return ResponseEntity.ok().body(dealership);
	}

	@GetMapping("/dealerships")
	public ResponseEntity<List<Dealership>> findAll() {
		List<Dealership> dealerships = dealersipService.findAll();
		return ResponseEntity.ok().body(dealerships);
	}
	
	@GetMapping("/dealerships/{dealershipName}")
	public ResponseEntity<List<Dealership>> findAll(@PathVariable String dealershipName) {
		List<Dealership> dealerships = dealersipService.findByNameStartsWith(dealershipName);
		return ResponseEntity.ok().body(dealerships);
	}
	
	@GetMapping("/dealerships/site/{id}")
	public ResponseEntity<List<Dealership>> findAll(@PathVariable Long id) {
		List<Dealership> dealerships = dealersipService.findBySiteId(id);
		return ResponseEntity.ok().body(dealerships);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Dealership> findById(@PathVariable Long id) {
		Dealership dealership = dealersipService.findById(id);
		return ResponseEntity.ok().body(dealership);
	}

}
