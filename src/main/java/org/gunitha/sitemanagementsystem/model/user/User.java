package org.gunitha.sitemanagementsystem.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.account.Application;
import org.gunitha.sitemanagementsystem.model.role.Authority;
import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(User.DISCRIMINATOR_VALUE)
@Table(name = "users")
public class User {

	public static final String DISCRIMINATOR_VALUE = "NA";

	@Id
	@GeneratedValue(generator = "USER_SEQUENCE")
	@SequenceGenerator(sequenceName = "USER_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "USER_SEQUENCE")
	private Long id;

	@Column(unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;

	private String phone;

	@Builder.Default
	private Boolean expired = false;

	@Builder.Default
	private Boolean locked = false;

	@Builder.Default
	private Boolean credentialExpired = false;

	@Builder.Default
	private Boolean enabled = true;

//	@Builder.Default
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//	private List<Role> roles = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private RoleLevel roleLevel;

	@Enumerated(EnumType.STRING)
	private PrivilegeType privilegeType;

	@Builder.Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Authority> authorities = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	public User(String username, String password, String firstName, String middleName, String lastName, String email,
			String phone, Boolean expired, Boolean locked, Boolean credentialExpired, Boolean enabled,
			List<Authority> authorities, Address address) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.expired = expired;
		this.locked = locked;
		this.credentialExpired = credentialExpired;
		this.enabled = enabled;
		this.authorities = authorities;
		this.address = address;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@PrimaryKeyJoinColumn
	private Application application;

}