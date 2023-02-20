package org.gunitha.sitemanagementsystem.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.gunitha.sitemanagementsystem.model.user.SiteOwner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Site {

	@Id
	@GeneratedValue(generator = "SITE_SEQUEMCE")
	@SequenceGenerator(sequenceName = "SITE_SEQUEMCE", initialValue = 1000, allocationSize = 20, name = "SITE_SEQUEMCE")
	private Long id;
	
	private String name;
	
	private String number;

	private Double latitude;

	private Double longitude;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address address;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Dealership dealership;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private SiteOwner siteOwner;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private SiteEngineer siteEngineer;	
	
}