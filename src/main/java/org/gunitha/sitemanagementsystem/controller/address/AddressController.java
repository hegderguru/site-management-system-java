package org.gunitha.sitemanagementsystem.controller.address;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms/application/address")
@CrossOrigin("http://localhost:3000")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping("/view/{id}")
	public ResponseEntity<Address> findById(@PathVariable Long id) {
		return ResponseEntity.ok(addressService.findById(id));
	}

}
