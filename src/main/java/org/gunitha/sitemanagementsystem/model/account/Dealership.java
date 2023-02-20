package org.gunitha.sitemanagementsystem.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;

import org.gunitha.sitemanagementsystem.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dealership {

	@Id
	@GeneratedValue(generator = "DEALERSHIP_SEQUENCE")
	@SequenceGenerator(sequenceName = "DEALERSHIP_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "DEALERSHIP_SEQUENCE")
	private Long id;

	@Column(unique = true)
	private String number;

	private String name;
	
	private String email;
	
	private String phone;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@PrimaryKeyJoinColumn
	private Account account;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	public Dealership(String name, String number,String email, String phone,Address address, Account account) {
		super();
		this.number = number;
		this.name = name;
		this.email=email;
		this.phone=phone;
		this.account = account;
		this.address=address;
	}

}