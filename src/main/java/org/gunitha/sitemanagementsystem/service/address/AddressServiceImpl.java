package org.gunitha.sitemanagementsystem.service.address;

import java.util.Optional;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Override
	public Address findById(Long id) {
		Optional<Address> address = addressRepository.findById(id);
		return address.isPresent() ? address.get() : null;
	}

}
