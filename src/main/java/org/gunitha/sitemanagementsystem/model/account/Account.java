package org.gunitha.sitemanagementsystem.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import org.gunitha.sitemanagementsystem.model.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Account {

	@Id
	@GeneratedValue(generator = "ACCOUNT_SEQUENCE")
	@SequenceGenerator(sequenceName = "ACCOUNT_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "ACCOUNT_SEQUENCE")
	private Long id;

	private String name;
	
	private String number;
	
	private String email;
	
	private String phone;

//	@JoinColumn(name = "application_id", referencedColumnName = "id")
	@ManyToOne(cascade = CascadeType.REFRESH)
	@PrimaryKeyJoinColumn
	private Application application;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;

	public Account(String name, String number,String email, String phone,Address address, Application application) {
		super();
		this.number = number;
		this.name = name;
		this.email=email;
		this.phone=phone;
		this.application = application;
		this.address=address;
	}
	
	

}