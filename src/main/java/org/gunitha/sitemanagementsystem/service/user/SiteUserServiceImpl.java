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
import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.gunitha.sitemanagementsystem.model.user.SiteOwner;
import org.gunitha.sitemanagementsystem.model.user.UserType;
import org.gunitha.sitemanagementsystem.repository.account.DealershipRepository;
import org.gunitha.sitemanagementsystem.repository.user.CustomUserRepository;
import org.gunitha.sitemanagementsystem.repository.user.SiteEngineerRepository;
import org.gunitha.sitemanagementsystem.repository.user.SiteOwnerRepository;
import org.gunitha.sitemanagementsystem.service.account.ApplicationService;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.gunitha.sitemanagementsystem.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SiteUserServiceImpl implements SiteUserService {

	@Autowired
	SiteOwnerRepository siteOwnerRepository;

	@Autowired
	SiteEngineerRepository siteEngineerRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AddressService addressService;

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CustomUserRepository customUserRepository;
	
	@Autowired
	DealershipRepository dealershipRepository;

	@Override
	public SiteOwner findSiteOwnerById(Long id) {
		return siteOwnerRepository.findById(id).get();
	}

	@Override
	public SiteOwner findSiteOwnerByUsername(String username) {
		return siteOwnerRepository.findByUsername(username);
	}

	@Override
	public SiteOwner registerSiteOwner(SiteOwner siteOwner, Boolean admin, String privilege) {

		Role role = roleService.findByRoleLevelAndPrivilegeType(admin ? RoleLevel.ADMIN : RoleLevel.USER,
				PrivilegeType.valueOf(privilege));
		siteOwner.setAuthorities(
				Arrays.asList(new Authority(role, siteOwner, UserType.SITE_OWNER_USER, AuthorityLevel.SITE)));
		siteOwner.setPassword(passwordEncoder.encode(siteOwner.getPassword()));

		return siteOwnerRepository.save(siteOwner);
	}

	@Override
	public SiteEngineer findSiteEngineerById(Long id) {
		return siteEngineerRepository.findById(id).get();
	}

	@Override
	public SiteEngineer findSiteEngineerByUsername(String username) {
		return siteEngineerRepository.findByUsername(username);
	}

	
	@Override
	public SiteEngineer registerOrUpdateSiteEngineer(UserBean userBean) {
		SiteEngineer siteEngineer = transformToSiteEngineer(userBean);
		siteEngineerRepository.save(siteEngineer);
		return siteEngineer;
	}

	private SiteEngineer transformToSiteEngineer(UserBean userBean) {
		Role role = roleService.findByRoleLevelAndPrivilegeType(
				RoleLevel.ADMIN.equals(RoleLevel.valueOf(userBean.getRoleLevel())) ? RoleLevel.ADMIN : RoleLevel.USER,
				PrivilegeType.valueOf(
						userBean.getPrivilegeType() != null ? userBean.getPrivilegeType() : PrivilegeType.READ.name()));

		Address address = null;
		if (null != userBean.getId() && userBean.getAddress()!=null && userBean.getAddress().getId()!=null) {
			address = addressService.findById(userBean.getAddress().getId());
		} else {
			address = new Address();
			address.setAddressType(AddressType.CURRENT);
		}
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

		SiteEngineer siteEngineer = null;
		if (null == userBean.getId()) {
			siteEngineer = new SiteEngineer();
			siteEngineer.setAuthorities(
					Arrays.asList(new Authority(role, siteEngineer, UserType.SITE_ENGINEER_USER, AuthorityLevel.SITE)));
			siteEngineer.setApplication(applicationService.findById(userBean.getApplicationId()));
		} else {
			siteEngineer = siteEngineerRepository.findById(userBean.getId()).get();			
		}
		
		siteEngineer.setFirstName(userBean.getFirstName());
		siteEngineer.setMiddleName(userBean.getMiddleName());
		siteEngineer.setLastName(userBean.getLastName());
		siteEngineer.setEmail(userBean.getEmail());
		siteEngineer.setPhone(userBean.getPhone());
		siteEngineer.setPassword(passwordEncoder.encode(userBean.getPassword()));
		siteEngineer.setUsername(userBean.getUsername());
		siteEngineer.setAddress(address);
		siteEngineer.setRoleLevel(RoleLevel.valueOf(userBean.getRoleLevel()));
		siteEngineer.setPrivilegeType(role.getPrivilegeType());
		siteEngineer.setDealerships(dealershipRepository.findAllByIds(userBean.getDealershipIds()));

		return siteEngineer;
	}
	
	@Override
	public SiteOwner registerOrUpdateSiteOwner(UserBean userBean) {
		SiteOwner siteOwner = transformToSiteOwner(userBean);
		siteOwnerRepository.save(siteOwner);
		return siteOwner;
	}

	private SiteOwner transformToSiteOwner(UserBean userBean) {
		Role role = roleService.findByRoleLevelAndPrivilegeType(
				RoleLevel.ADMIN.equals(RoleLevel.valueOf(userBean.getRoleLevel())) ? RoleLevel.ADMIN : RoleLevel.USER,
				PrivilegeType.valueOf(
						userBean.getPrivilegeType() != null ? userBean.getPrivilegeType() : PrivilegeType.READ.name()));

		Address address = null;
		if (null != userBean.getId() && userBean.getAddress()!=null && userBean.getAddress().getId()!=null) {
			address = addressService.findById(userBean.getAddress().getId());
		} else {
			address = new Address();
			address.setAddressType(AddressType.CURRENT);
		}
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

		SiteOwner siteOwner = null;
		if (null == userBean.getId()) {
			siteOwner = new SiteOwner();
			siteOwner.setAuthorities(
					Arrays.asList(new Authority(role, siteOwner, UserType.SITE_OWNER_USER, AuthorityLevel.SITE)));
			siteOwner.setApplication(applicationService.findById(userBean.getApplicationId()));
		} else {
			siteOwner = siteOwnerRepository.findById(userBean.getId()).get();			
		}
		
		siteOwner.setFirstName(userBean.getFirstName());
		siteOwner.setMiddleName(userBean.getMiddleName());
		siteOwner.setLastName(userBean.getLastName());
		siteOwner.setEmail(userBean.getEmail());
		siteOwner.setPhone(userBean.getPhone());
		siteOwner.setPassword(passwordEncoder.encode(userBean.getPassword()));
		siteOwner.setUsername(userBean.getUsername());
		siteOwner.setAddress(address);
		siteOwner.setRoleLevel(RoleLevel.valueOf(userBean.getRoleLevel()));
		siteOwner.setPrivilegeType(role.getPrivilegeType());
		siteOwner.setDealerships(dealershipRepository.findAllByIds(userBean.getDealershipIds()));

		return siteOwner;
	}

	@Override
	public List<SiteEngineer> findAllSiteEngineers() {
		return siteEngineerRepository.findAll();
	}
	
	@Override
	public List<SiteOwner> findAllSiteOwners() {
		return siteOwnerRepository.findAll();
	}

	@Override
	public List<SiteEngineer> findAllSiteEngineersStartsWithUserName(String startsWithUserName) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return customUserRepository.findAllSiteEngineersStartsWithUserName(startsWithUserName,userName);
	}

	@Override
	public List<SiteOwner> findAllOwnersStartsWithUserName(String startsWithUserName) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return customUserRepository.findAllSiteOwnersStartsWithUserName(startsWithUserName,userName);
	}

}
