package org.gunitha.sitemanagementsystem.model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.account.Account;
import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.model.role.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(DealerUser.DISCRIMINATOR_VALUE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealerUser extends User {

	public static final String DISCRIMINATOR_VALUE = "DEALER";
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "DEALERSHIP_DEALERUSER", joinColumns = @JoinColumn(name = "DEALER_USER", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "DEALERSHIP", referencedColumnName = "ID"))
	private List<Dealership> dealerships;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@PrimaryKeyJoinColumn
	private Account account;
	
	public DealerUser(String username, String password, String firstName, String middleName, String lastName,
			String email, String phone, Boolean expired, Boolean locked, Boolean credentialExpired, Boolean enabled,
			List<Authority> authorities, Address address) {
		super(username, password, firstName, middleName, lastName, email, phone, expired, locked, credentialExpired,
				enabled, authorities, address);
	}
	
	

}