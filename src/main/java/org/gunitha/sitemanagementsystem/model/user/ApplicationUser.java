package org.gunitha.sitemanagementsystem.model.user;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.role.Authority;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(ApplicationUser.DISCRIMINATOR_VALUE)
@NoArgsConstructor
@Data
public class ApplicationUser extends User {

	public static final String DISCRIMINATOR_VALUE = "APPLICATION";

	public ApplicationUser(String username, String password, String firstName, String middleName, String lastName,
			String email, String phone, Boolean expired, Boolean locked, Boolean credentialExpired, Boolean enabled,
			List<Authority> authorities, Address address) {
		super(username, password, firstName, middleName, lastName, email, phone, expired, locked, credentialExpired,
				enabled, authorities, address);
	}

}