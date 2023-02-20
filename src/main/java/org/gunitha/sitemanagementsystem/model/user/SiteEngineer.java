package org.gunitha.sitemanagementsystem.model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.model.account.Site;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(SiteEngineer.DISCRIMINATOR_VALUE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SiteEngineer extends User {

	public static final String DISCRIMINATOR_VALUE = "SITE_ENGINEER";
	
	@JsonIgnore
	@OneToMany(mappedBy = "siteEngineer",cascade = CascadeType.ALL)
	private List<Site> sites;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "DEALERSHIP_SITE_ENGINEER", joinColumns = @JoinColumn(name = "SITE_ENGINEER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "DEALERSHIP", referencedColumnName = "ID"))
	private List<Dealership> dealerships;

}