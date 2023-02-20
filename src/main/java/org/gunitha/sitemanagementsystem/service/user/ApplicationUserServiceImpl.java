package org.gunitha.sitemanagementsystem.service.user;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.AddressType;
import org.gunitha.sitemanagementsystem.model.role.Authority;
import org.gunitha.sitemanagementsystem.model.role.AuthorityLevel;
import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.Role;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.model.user.ApplicationUser;
import org.gunitha.sitemanagementsystem.model.user.UserType;
import org.gunitha.sitemanagementsystem.repository.user.ApplicationUserRepository;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.gunitha.sitemanagementsystem.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService {

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	AddressService addressService;

	@Override
	public ApplicationUser register(ApplicationUser applicationUser) {
		return applicationUserRepository.save(applicationUser);
	}

	@Override
	public ApplicationUser findApplicationUser(Long applicationUserId) {
		return applicationUserRepository.findById(applicationUserId).get();
	}

	@Override
	public ApplicationUser findByUsername(String applicationUsername) {
		return applicationUserRepository.findByUsername(applicationUsername);
	}

	@Override
	public ApplicationUser findById(Long id) {
		return applicationUserRepository.findById(id).get();
	}

	@Override
	public List<ApplicationUser> findAll() {
		return applicationUserRepository.findAll();
	}

	@Override
	public ApplicationUser transformToApplicationUser(UserBean userBean) {

		Role role = roleService.findByRoleLevelAndPrivilegeType(
				userBean.getRoleLevel() != null && RoleLevel.ADMIN.equals(RoleLevel.valueOf(userBean.getRoleLevel()))
						? RoleLevel.ADMIN
						: RoleLevel.USER,
				PrivilegeType.valueOf(
						userBean.getPrivilegeType() != null ? userBean.getPrivilegeType() : PrivilegeType.READ.name()));

		Address address = null;
		if (null == userBean.getId()) {
			address = new Address(userBean.getAddress().getNumber(), userBean.getAddress().getNumber(),
					userBean.getAddress().getAddressLine1(), userBean.getAddress().getAddressLine2(),
					userBean.getAddress().getAddressLine3(), null, userBean.getAddress().getCity(),
					userBean.getAddress().getState(), userBean.getAddress().getCountry(),
					userBean.getAddress().getZipCode(), AddressType.CURRENT);
		} else {
			address = addressService.findById(userBean.getAddress().getId());
			address.setName(userBean.getAddress().getName());
			address.setNumber(userBean.getAddress().getNumber());
			address.setAddressLine1(userBean.getAddress().getAddressLine1());
			address.setAddressLine2(userBean.getAddress().getAddressLine2());
			address.setAddressLine3(userBean.getAddress().getAddressLine3());
			address.setVillage(userBean.getAddress().getVillage());
			address.setCity(userBean.getAddress().getCity());
			address.setState(userBean.getAddress().getState());
			address.setCountry(userBean.getAddress().getCountry());
			address.setZipCode(userBean.getAddress().getZipCode());
		}

		ApplicationUser applicationUser = null;
		if (null == userBean.getId()) {
			applicationUser = new ApplicationUser(userBean.getUsername(),
					passwordEncoder.encode(userBean.getPassword()), userBean.getFirstName(), userBean.getMiddleName(),
					userBean.getLastName(), userBean.getEmail(), userBean.getPhone(), false, false, false, true, null,
					address);
			applicationUser
					.setAuthorities(Arrays.asList(new Authority(role, applicationUser, UserType.APPLICATION_USER,AuthorityLevel.APPLICATION)));
			applicationUser.setApplication(applicationService.findAll().iterator().next());
		} else {
			applicationUser = findById(userBean.getId());
			applicationUser.setFirstName(userBean.getFirstName());
			applicationUser.setMiddleName(userBean.getMiddleName());
			applicationUser.setLastName(userBean.getLastName());
			applicationUser.setEmail(userBean.getEmail());
			applicationUser.setPhone(userBean.getPhone());
			applicationUser.setPassword(passwordEncoder.encode(userBean.getPassword()));
			applicationUser.setUsername(userBean.getUsername());
			applicationUser.setAddress(address);
			applicationUser.getAuthorities().clear();
			applicationUser.getAuthorities()
					.addAll(Arrays.asList(new Authority(role, applicationUser, UserType.APPLICATION_USER,AuthorityLevel.APPLICATION)));
		}

		applicationUser.setRoleLevel(RoleLevel.valueOf(userBean.getRoleLevel()));
		applicationUser.setPrivilegeType(role.getPrivilegeType());

		return applicationUser;
	}

	@Override
	public ApplicationUser update(ApplicationUser applicationUser) {
		return applicationUserRepository.save(applicationUser);
	}

}
