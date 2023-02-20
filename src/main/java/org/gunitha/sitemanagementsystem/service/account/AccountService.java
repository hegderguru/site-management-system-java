package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.AccountBean;
import org.gunitha.sitemanagementsystem.model.account.Account;

public interface AccountService {

	public Account register(Account account);
	
	public Account findByName(String name);
	
	public Account findById(Long id);

	public Account transformToAccount(AccountBean accountBean, Long applicationId);

	public Account transformToAccount(AccountBean accountBean);

	public Account update(Account account);
	
	public List<Account> findAll();
}
