package org.gunitha.sitemanagementsystem.controller.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealershipBean {
	private Long id;
	private String name;
	private String number;
	private String email;
	private String phone;
	private AddressBean address;
}
