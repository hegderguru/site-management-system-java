package org.gunitha.sitemanagementsystem.security;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.model.user.UserDetail;
import org.gunitha.sitemanagementsystem.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UserDetail(userRepository.findByUsername(username));
	}

}
