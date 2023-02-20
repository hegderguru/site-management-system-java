package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.beans.ApplicationBean;
import org.gunitha.sitemanagementsystem.model.account.Application;
import org.gunitha.sitemanagementsystem.repository.account.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;
	
	public Application register(Application application) {
		return applicationRepository.save(application);
	}

	@Override
	public Application findApplicationByName(String name) {
		return applicationRepository.findByName(name).get(0);
	}

	@Override
	public Application findById(Long id) {
		return applicationRepository.findById(id).get();
	}
	
	@Override
	public Application transformToAccount(ApplicationBean applicationBean) {
		Application application = new Application();
		application.setName(applicationBean.getName());
		application.setNumber(applicationBean.getNumber());
		application.setEmail(applicationBean.getEmail());
		application.setPhone(applicationBean.getPhone());
		return application;
	}

	@Override
	public List<Application> findAll() {
		return applicationRepository.findAll();
	}

	
}
