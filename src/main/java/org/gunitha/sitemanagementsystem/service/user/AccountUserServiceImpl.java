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
import org.gunitha.sitemanagementsystem.model.user.AccountUser;
import org.gunitha.sitemanagementsystem.model.user.UserType;
import org.gunitha.sitemanagementsystem.repository.account.AccountRepository;
import org.gunitha.sitemanagementsystem.repository.user.AccountUserRepository;
import org.gunitha.sitemanagementsystem.service.account.AccountService;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.gunitha.sitemanagementsystem.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountUserServiceImpl implements AccountUserService {

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	AccountUserRepository accountUserRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AddressService addressService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public AccountUser register(UserBean userBean) {
		AccountUser accountUser = transformToAccountUser(userBean);			
		return accountUserRepository.save(accountUser);
	}

	@Override
	public AccountUser findById(Long accountUserId) {
		return accountUserRepository.findById(accountUserId).get();
	}

	@Override
	public AccountUser findByUsername(String accountUsername) {
		return accountUserRepository.findByUsername(accountUsername);
	}

	@Override
	public AccountUser transformToAccountUser(UserBean userBean) {

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

		AccountUser accountUser = null;
		if (null == userBean.getId()) {
			accountUser = new AccountUser(userBean.getUsername(), passwordEncoder.encode(userBean.getPassword()),
					userBean.getFirstName(), userBean.getMiddleName(), userBean.getLastName(), userBean.getEmail(),
					userBean.getPhone(), false, false, false, true, null, address);
			accountUser.setAuthorities(Arrays.asList(new Authority(role, accountUser,UserType.ACCOUNT_USER,AuthorityLevel.ACCOUNT)));
			accountUser.setApplication(applicationService.findById(userBean.getApplicationId()));
			accountUser.setAccount(accountRepository.findById(userBean.getAccountId()).get());
		} else {
			accountUser = findById(userBean.getId());
			accountUser.setFirstName(userBean.getFirstName());
			accountUser.setMiddleName(userBean.getMiddleName());
			accountUser.setLastName(userBean.getLastName());
			accountUser.setEmail(userBean.getEmail());
			accountUser.setPhone(userBean.getPhone());
			accountUser.setPassword(passwordEncoder.encode(userBean.getPassword()));
			accountUser.setUsername(userBean.getUsername());
			accountUser.setAddress(address);
			accountUser.getAuthorities().clear();
			accountUser.getAuthorities()
					.addAll(Arrays.asList(new Authority(role, accountUser,UserType.ACCOUNT_USER,AuthorityLevel.ACCOUNT)));
		}

		accountUser.setRoleLevel(RoleLevel.valueOf(userBean.getRoleLevel()));
		accountUser.setPrivilegeType(role.getPrivilegeType());

		return accountUser;
	}

	@Override
	public List<AccountUser> findAll() {
		return accountUserRepository.findAll();
	}

}
