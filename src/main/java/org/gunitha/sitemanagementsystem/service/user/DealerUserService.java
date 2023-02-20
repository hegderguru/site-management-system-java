package org.gunitha.sitemanagementsystem.service.user;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.user.DealerUser;

public interface DealerUserService {

	DealerUser findByUsername(String username);

	DealerUser findById(Long id);

	DealerUser register(DealerUser dealerUser);

	DealerUser register(DealerUser dealerUser, Boolean admin, String privilege);

	DealerUser transformToDealerUser(UserBean userBean);

	List<DealerUser> findAll();

}
