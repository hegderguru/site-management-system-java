package org.gunitha.sitemanagementsystem.repository.account;

import java.util.List;

import org.gunitha.sitemanagementsystem.model.account.Dealership;

public interface CustomDealershipRepository {
	
	public List<Dealership> userDealerships(Long userId);
	
	public List<Dealership> dealershipsStartsWithName(String name);

	public List<Dealership> findBySiteId(Long id);
	
}
