package org.gunitha.sitemanagementsystem.service.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.AddressType;
import org.gunitha.sitemanagementsystem.model.user.User;
import org.gunitha.sitemanagementsystem.repository.user.UserRepository;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressService addressService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public User transformToAccount(UserBean userBean) {
		Address address = null;
		if (null == userBean.getId()) {
			address = new Address(userBean.getAddress().getNumber(), userBean.getAddress().getNumber(),
					userBean.getAddress().getAddressLine1(), userBean.getAddress().getAddressLine2(),
					userBean.getAddress().getAddressLine3(), null, userBean.getAddress().getCity(),
					userBean.getAddress().getState(), userBean.getAddress().getCountry(),
					userBean.getAddress().getZipCode(), AddressType.CURRENT);
		} else {
			address = addressService.findById(userBean.getId());
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

		User user = null;
		if (null == userBean.getId()) {
			user = new User(userBean.getUsername(), passwordEncoder.encode(userBean.getPassword()),
					userBean.getFirstName(), userBean.getMiddleName(), userBean.getLastName(), userBean.getEmail(),
					userBean.getPhone(), false, false, false, true, null, address);
		} else {
			user = findById(userBean.getId());
			user.setFirstName(userBean.getFirstName());
			user.setMiddleName(userBean.getMiddleName());
			user.setLastName(userBean.getLastName());
			user.setEmail(userBean.getEmail());
			user.setPhone(userBean.getPhone());
			user.setPassword(passwordEncoder.encode(userBean.getPassword()));
			user.setUsername(userBean.getUsername());
			user.setAddress(address);
		}

		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
