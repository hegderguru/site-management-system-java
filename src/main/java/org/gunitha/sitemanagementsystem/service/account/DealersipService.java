package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.controller.beans.DealershipBean;
import org.gunitha.sitemanagementsystem.model.account.Dealership;

public interface DealersipService {

	public Dealership register(Dealership dealership);
	
	public Dealership update(Dealership dealership);
	
	public Dealership findByName(String name);
	
	public Dealership findById(Long id);

	public Dealership transformToAccount(DealershipBean dealershipBean, Long accountId);

	public List<Dealership> findAll();

	public List<Dealership> findAll(String idOrNameOrNumber);

	public List<Dealership> findAllByIds(List<Long> ids);

	public Dealership transformToAccount(DealershipBean dealershipBean);

	public List<Dealership> findByNameStartsWith(String dealershipName);

	public Dealership register(DealershipBean dealershipBean, Long accountId);

	public List<Dealership> findBySiteId(Long id);
}
