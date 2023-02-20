package org.gunitha.sitemanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Address {

	@Id
	@GeneratedValue(generator = "ADDRESS_SEQUENCE")
	@SequenceGenerator(sequenceName = "ADDRESS_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "ADDRESS_SEQUENCE")
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

	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	public Address(String name, String number, String addressLine1, String addressLine2, String addressLine3,
			String village, String city, String state, String country, String zipCode, AddressType addressType) {
		super();
		this.number = number;
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.village = village;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.addressType = addressType;
	}
	
}



