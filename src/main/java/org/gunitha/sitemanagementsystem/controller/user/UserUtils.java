package org.gunitha.sitemanagementsystem.controller.user;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.user.ApplicationUser;

public class UserUtils {

	public static ApplicationUser transformApplicationUser(UserBean userBean) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setFirstName(userBean.getFirstName());
		applicationUser.setMiddleName(userBean.getMiddleName());
		applicationUser.setLastName(userBean.getLastName());
		applicationUser.setUsername(userBean.getUsername());
		applicationUser.setPassword(userBean.getPassword());
		applicationUser.setEmail(userBean.getEmail());
		applicationUser.setPhone(userBean.getPhone());

		Address address = transformAddress(userBean);

		applicationUser.setAddress(address);

		return applicationUser;
	}

	private static Address transformAddress(UserBean userBean) {
		Address address = new Address();
		address.setNumber(userBean.getAddress().getNumber());
		address.setName(userBean.getAddress().getNumber());
		address.setAddressLine1(userBean.getAddress().getAddressLine1());
		address.setAddressLine2(userBean.getAddress().getAddressLine2());
		address.setAddressLine3(userBean.getAddress().getAddressLine3());
		address.setCity(userBean.getAddress().getCity());
		address.setState(userBean.getAddress().getState());
		address.setCountry(userBean.getAddress().getCountry());
		address.setZipCode(userBean.getAddress().getZipCode());
		return address;
	}

}
