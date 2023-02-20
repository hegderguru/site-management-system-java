package org.gunitha.sitemanagementsystem.model.role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import org.gunitha.sitemanagementsystem.model.user.User;
import org.gunitha.sitemanagementsystem.model.user.UserType;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Authority implements GrantedAuthority {

	@Id
	@GeneratedValue(generator = "AUTHORITY_SEQUENCE")
	@SequenceGenerator(sequenceName = "AUTHORITY_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "AUTHORITY_SEQUENCE")
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private AuthorityLevel authorityLevel;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Role role;
//
//	@Enumerated(EnumType.STRING)
//	private PrivilegeType privilegeType;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	@JsonIgnore
	private User user;

	public Authority(Role role, User user, UserType userType, AuthorityLevel authorityLevel) {
		super();
		this.name=userType+"_"+role.getName();
		this.role = role;
		this.user = user;
		this.authorityLevel=authorityLevel;
//		this.privilegeType=role.getPrivilegeType();
	}
	
	public Authority() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}
