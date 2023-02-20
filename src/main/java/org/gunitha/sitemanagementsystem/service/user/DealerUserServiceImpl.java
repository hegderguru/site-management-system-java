package org.gunitha.sitemanagementsystem.service.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.AddressType;
import org.gunitha.sitemanagementsystem.model.role.Authority;
import org.gunitha.sitemanagementsystem.model.role.AuthorityLevel;
import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.Role;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.gunitha.sitemanagementsystem.model.user.UserType;
import org.gunitha.sitemanagementsystem.repository.account.DealershipRepository;
import org.gunitha.sitemanagementsystem.repository.user.DealerUserRepository;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.gunitha.sitemanagementsystem.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DealerUserServiceImpl implements DealerUserService {

	@Autowired
	DealerUserRepository dealerUserRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	AddressService addressService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	DealershipRepository dealershipRepository;

	@Autowired
	ApplicationService applicationService;

	@Override
	public DealerUser register(DealerUser dealerUser) {
		return dealerUserRepository.save(dealerUser);
	}

	@Override
	public DealerUser register(DealerUser dealerUser, Boolean admin, String privilege) {
		Role role = roleService.findByRoleLevelAndPrivilegeType(admin ? RoleLevel.ADMIN : RoleLevel.USER,
				PrivilegeType.valueOf(privilege));
		dealerUser.setAuthorities(
				Arrays.asList(new Authority(role, dealerUser, UserType.DEALER_USER, AuthorityLevel.DEALERSHIP)));
		dealerUser.setPassword(passwordEncoder.encode(dealerUser.getPassword()));

		return dealerUserRepository.save(dealerUser);
	}

	@Override
	public DealerUser findByUsername(String username) {
		return dealerUserRepository.findByUsername(username);
	}

	@Override
	public DealerUser findById(Long id) {
		return dealerUserRepository.findById(id).get();
	}

	@Override
	public DealerUser transformToDealerUser(UserBean userBean) {

		Role role = roleService.findByRoleLevelAndPrivilegeType(
				RoleLevel.ADMIN.equals(RoleLevel.valueOf(userBean.getRoleLevel())) ? RoleLevel.ADMIN : RoleLevel.USER,
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

		DealerUser dealerUser = null;
		if (null == userBean.getId()) {
			dealerUser = new DealerUser(userBean.getUsername(), passwordEncoder.encode(userBean.getPassword()),
					userBean.getFirstName(), userBean.getMiddleName(), userBean.getLastName(), userBean.getEmail(),
					userBean.getPhone(), false, false, false, true, null, address);
			dealerUser.setAuthorities(
					Arrays.asList(new Authority(role, dealerUser, UserType.DEALER_USER, AuthorityLevel.DEALERSHIP)));
			dealerUser.setApplication(applicationService.findById(userBean.getApplicationId()));
		} else {
			dealerUser = findById(userBean.getId());
			dealerUser.setFirstName(userBean.getFirstName());
			dealerUser.setMiddleName(userBean.getMiddleName());
			dealerUser.setLastName(userBean.getLastName());
			dealerUser.setEmail(userBean.getEmail());
			dealerUser.setPhone(userBean.getPhone());
			dealerUser.setPassword(passwordEncoder.encode(userBean.getPassword()));
			dealerUser.setUsername(userBean.getUsername());
			dealerUser.setAddress(address);
			dealerUser.getAuthorities().clear();
			dealerUser.getAuthorities().addAll(
					Arrays.asList(new Authority(role, dealerUser, UserType.DEALER_USER, AuthorityLevel.DEALERSHIP)));
		}

		dealerUser.setDealerships(userBean.getDealershipIds().stream()
				.map(id -> dealershipRepository.findById(id).get()).collect(Collectors.toList()));
		dealerUser.setAccount(dealerUser.getDealerships().get(0).getAccount());
		dealerUser.setRoleLevel(RoleLevel.valueOf(userBean.getRoleLevel()));
		dealerUser.setPrivilegeType(role.getPrivilegeType());

		return dealerUser;
	}

	@Override
	public List<DealerUser> findAll() {
		return dealerUserRepository.findAll();
	}

}
