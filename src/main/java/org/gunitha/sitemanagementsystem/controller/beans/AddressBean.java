package org.gunitha.sitemanagementsystem.controller.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressBean {
	
	private Long id;
	private String name;
	private String number;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String village;
	private String city;
	private String state;
	private String country;
	private String zipCode;

}
