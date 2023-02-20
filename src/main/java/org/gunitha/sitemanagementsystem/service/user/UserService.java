package org.gunitha.sitemanagementsystem.service.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.user.User;

public interface UserService {

	public User registerUser(User user);
	
	public User findByUsername(String username);

	public User transformToAccount(UserBean userBean);

	public User findById(Long id);
	
	public List<User> findAll();
}
