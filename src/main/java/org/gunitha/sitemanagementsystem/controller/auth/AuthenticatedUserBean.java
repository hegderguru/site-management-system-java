package org.gunitha.sitemanagementsystem.controller.auth;

import org.gunitha.sitemanagementsystem.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticatedUserBean {

	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String username;
	private String email;
	private String phone;
	private String authorityLevel;
	private String authorityName;
	private String application;
	private Long applicationId;
	private Long accountId;
	private Long dealershipId;

	public AuthenticatedUserBean(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.middleName = user.getMiddleName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.authorityName = user.getAuthorities().get(0).getName();
		this.authorityLevel = user.getAuthorities().get(0).getAuthorityLevel().name();
		this.application = user.getApplication().getName();
		this.applicationId = user.getApplication().getId();
	}

}
