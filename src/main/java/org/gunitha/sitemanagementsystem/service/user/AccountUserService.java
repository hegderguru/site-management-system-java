package org.gunitha.sitemanagementsystem.service.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.user.AccountUser;

public interface AccountUserService {

	public AccountUser register(UserBean userBean);

	public AccountUser findById(Long  accountUserId);
	
	public AccountUser findByUsername(String  accountUsername);

	public AccountUser transformToAccountUser(UserBean userBean);

	public List<AccountUser> findAll();

	
}
