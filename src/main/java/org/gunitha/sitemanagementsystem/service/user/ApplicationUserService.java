package org.gunitha.sitemanagementsystem.service.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.user.ApplicationUser;

public interface ApplicationUserService {

	public ApplicationUser register(ApplicationUser applicationUser);
	
	public ApplicationUser findApplicationUser(Long  applicationUserId);
	
	public ApplicationUser findByUsername(String  applicationUsername);

	public ApplicationUser findById(Long id);
	
	public List<ApplicationUser> findAll();

	public ApplicationUser transformToApplicationUser(UserBean userBean);

	public ApplicationUser update(ApplicationUser applicationUser);

	
}
