package org.gunitha.sitemanagementsystem.controller.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.AddressBean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserBean {
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String username;
	private String password;
	private String repeatPassword;
	private String email;
	private String phone;
	private String roleLevel;
	private String privilegeType;
	private AddressBean address;
	private List<Long> dealershipIds;
	private Long applicationId;
	private Long accountId;	
	private Long dealershipId;
	
	public UserBean(Long id, String firstName, String lastName, String middleName, String username, String password,
			String repeatPassword, String email, String phone, String roleLevel, String privilegeType,
			AddressBean address, List<Long> dealershipIds, Long applicationId, Long accountId,Long dealershipId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.email = email;
		this.phone = phone;
		this.roleLevel = roleLevel;
		this.privilegeType = privilegeType;
		this.address = address;
		this.dealershipIds = dealershipIds;
		this.applicationId = applicationId;
		this.accountId = accountId;
		this.dealershipId=dealershipId;
	}
	
	

}
