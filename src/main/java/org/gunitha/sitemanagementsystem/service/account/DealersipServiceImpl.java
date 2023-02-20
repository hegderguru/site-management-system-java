package org.gunitha.sitemanagementsystem.service.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.beans.AddressBean;
import org.gunitha.sitemanagementsystem.controller.beans.DealershipBean;
import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.AddressType;
import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.gunitha.sitemanagementsystem.repository.account.CustomDealershipRepository;
import org.gunitha.sitemanagementsystem.repository.account.DealershipRepository;
import org.gunitha.sitemanagementsystem.service.user.DealerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

@Service
@Transactional
public class DealersipServiceImpl implements DealersipService {

	@Autowired
	DealershipRepository dealershipRepository;
	
	@Autowired
	CustomDealershipRepository customdealershipRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	DealerUserService dealerUserService;

	@Override
	public Dealership register(Dealership dealership) {		
		return dealershipRepository.save(dealership);
	}
	
	@Override
	public Dealership register(DealershipBean dealershipBean, Long accountId) {
		Dealership dealership = transformToAccount(dealershipBean, accountId);		
		dealership = register(dealership);
		DealerUser transformToDealerUser = dealerUserService.transformToDealerUser(new UserBean(null, dealership.getNumber(), dealership.getNumber(),
				dealership.getNumber(), dealership.getEmail(), dealership.getEmail(), dealership.getEmail(),
				dealership.getEmail(), dealership.getPhone(), RoleLevel.ADMIN.name(), PrivilegeType.WRITE.name(),
				new AddressBean(null, dealership.getAddress().getName(), dealership.getAddress().getNumber(),
						dealership.getAddress().getAddressLine1(), dealership.getAddress().getAddressLine2(),
						dealership.getAddress().getAddressLine3(), dealership.getAddress().getVillage(),
						dealership.getAddress().getCity(), dealership.getAddress().getState(),
						dealership.getAddress().getCountry(), dealership.getAddress().getZipCode()),
				Arrays.asList(dealership.getId()), dealership.getAccount().getApplication().getId(), dealership.getAccount().getId(), dealership.getId()));
		dealerUserService.register(transformToDealerUser);
		return dealership;
	}


	@Override
	public Dealership findByName(String name) {
		return dealershipRepository.findByName(name).get(0);
	}

	@Override
	public Dealership findById(Long id) {
		return dealershipRepository.findById(id).get();
	}

	@Override
	public Dealership transformToAccount(DealershipBean dealershipBean, Long accountId) {
		Address address = new Address(dealershipBean.getAddress().getNumber(), dealershipBean.getAddress().getNumber(),
				dealershipBean.getAddress().getAddressLine1(), dealershipBean.getAddress().getAddressLine2(),
				dealershipBean.getAddress().getAddressLine3(), null, dealershipBean.getAddress().getCity(),
				dealershipBean.getAddress().getState(), dealershipBean.getAddress().getCountry(),
				dealershipBean.getAddress().getZipCode(), AddressType.CURRENT);

		Dealership dealership = new Dealership(dealershipBean.getName(), dealershipBean.getNumber(),
				dealershipBean.getEmail(), dealershipBean.getPhone(), address, accountService.findById(accountId));

		return dealership;
	}

	@Override
	public Dealership transformToAccount(DealershipBean dealershipBean) {
		Optional<Dealership> findById = dealershipRepository.findById(dealershipBean.getId());
		Dealership dealership = findById.get();
		dealership.setName(dealershipBean.getName());
		dealership.setNumber(dealershipBean.getNumber());
		dealership.setEmail(dealershipBean.getEmail());
		dealership.setPhone(dealershipBean.getPhone());
		dealership.getAddress().setName(dealershipBean.getAddress().getName());
		dealership.getAddress().setNumber(dealershipBean.getAddress().getNumber());
		dealership.getAddress().setAddressLine1(dealershipBean.getAddress().getAddressLine1());
		dealership.getAddress().setAddressLine2(dealershipBean.getAddress().getAddressLine2());
		dealership.getAddress().setAddressLine3(dealershipBean.getAddress().getAddressLine3());
		dealership.getAddress().setCity(dealershipBean.getAddress().getCity());
		dealership.getAddress().setState(dealershipBean.getAddress().getState());
		dealership.getAddress().setCountry(dealershipBean.getAddress().getCountry());
		dealership.getAddress().setZipCode(dealershipBean.getAddress().getZipCode());
		return dealership;
	}

	@Override
	public List<Dealership> findAll() {
		return dealershipRepository.findAll();
	}

	@Override
	public List<Dealership> findAll(String nameOrNumber) {
		Set<Dealership> dealerships = new HashSet<Dealership>();
		List<Dealership> findByName = dealershipRepository.findByName(nameOrNumber);
		List<Dealership> findByNumber = dealershipRepository.findByNumber(nameOrNumber);
		dealerships.addAll(findByName);
		dealerships.addAll(findByNumber);
		try {
			Long parseNumber = NumberUtils.parseNumber(nameOrNumber, Long.class);
			Optional<Dealership> findById = dealershipRepository.findById(parseNumber);
			if (findById.isPresent()) {
				dealerships.add(findById.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ArrayList<>(dealerships);
	}

	@Override
	public List<Dealership> findAllByIds(List<Long> ids) {
		List<Dealership> dealerships = ids.stream().map(id -> findById(id)).collect(Collectors.toList());
		return dealerships;
	}

	@Override
	public Dealership update(Dealership dealership) {
		return dealershipRepository.save(dealership);
	}

	@Override
	public List<Dealership> findByNameStartsWith(String name) {
		return customdealershipRepository.dealershipsStartsWithName(name);
	}

	@Override
	public List<Dealership> findBySiteId(Long id) {
		return customdealershipRepository.findBySiteId(id);
	}
	
}
