package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.ApplicationBean;
import org.gunitha.sitemanagementsystem.model.account.Account;
import org.gunitha.sitemanagementsystem.model.account.Application;

public interface ApplicationService {

	public Application register(Application application);
	
	public Application findApplicationByName(String name);
	
	public Application findById(Long id);
	
	public List<Application> findAll();

	public Application transformToAccount(ApplicationBean applicationBean);
}
