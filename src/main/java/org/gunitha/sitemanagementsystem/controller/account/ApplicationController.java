package org.gunitha.sitemanagementsystem.controller.account;

import org.gunitha.sitemanagementsystem.controller.beans.ApplicationBean;
import org.gunitha.sitemanagementsystem.model.account.Application;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@PostMapping("/register")
	public ResponseEntity<Application> registerApplication(@RequestBody ApplicationBean applicationBean) {
		Application application = applicationService.transformToAccount(applicationBean);
		applicationService.register(application);
		return ResponseEntity.ok().body(application);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Application> findApplication(@PathVariable String name) {
		Application addApplication = applicationService.findApplicationByName(name);
		return ResponseEntity.ok().body(addApplication);
	}

}
