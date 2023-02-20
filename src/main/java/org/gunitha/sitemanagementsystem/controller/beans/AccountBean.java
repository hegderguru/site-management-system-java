package org.gunitha.sitemanagementsystem.controller.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountBean {
	private Long id;
	private String name;
	private String number;
	private String email;
	private String phone;
	private Long applicationId;
	private AddressBean address;
}
