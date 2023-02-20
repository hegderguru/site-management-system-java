package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.beans.SiteBean;
import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.AddressType;
import org.gunitha.sitemanagementsystem.model.account.Site;
import org.gunitha.sitemanagementsystem.repository.account.DealershipRepository;
import org.gunitha.sitemanagementsystem.repository.account.SiteRepository;
import org.gunitha.sitemanagementsystem.repository.user.SiteEngineerRepository;
import org.gunitha.sitemanagementsystem.repository.user.SiteOwnerRepository;
import org.gunitha.sitemanagementsystem.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SiteServiceImpl implements SiteService {

	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	DealershipRepository dealershipRepository;
	
	@Autowired
	SiteEngineerRepository siteEngineerRepository;
	
	@Autowired
	SiteOwnerRepository siteOwnerRepository;

	@Override
	public Site register(Site site) {
		return siteRepository.save(site);
	}

	@Override
	public Site findByName(String name) {
		return siteRepository.findByName(name).get(0);
	}

	@Override
	public Site findByNumber(String number) {
		return siteRepository.findByNumber(number).get(0);
	}

	@Override
	public Site findById(Long id) {
		return siteRepository.findById(id).get();
	}

	@Override
	public Site register(SiteBean siteBean) {
		Site site = transformToSite(siteBean);
		return siteRepository.save(site);
	}

	private Site transformToSite(SiteBean siteBean) {
		
		Site site ;
		if(siteBean.getId()!=null) {
			site = siteRepository.findById(siteBean.getId()).get();
			siteBean.getAddress().setId(site.getAddress().getId());
		}
		else{
			site = new Site();
		}
		
		site.setName(siteBean.getName());
		site.setNumber(siteBean.getNumber());
		site.setLatitude(siteBean.getLatitude());
		site.setLongitude(siteBean.getLongitude());
		Address transformToAddress = TransformToAddress(siteBean);
		site.setAddress(transformToAddress);
		
		if(siteBean.getDealershipIds()!=null) {
			site.setDealership(dealershipRepository.findById(siteBean.getDealershipIds().get(0)).get());
		}	
		
		if(siteBean.getSiteEngineerId()!=null) {
			site.setSiteEngineer(siteEngineerRepository.findById(siteBean.getSiteEngineerId()).get());
		}
		
		if(siteBean.getSiteOwnerId()!=null) {
			site.setSiteOwner(siteOwnerRepository.findById(siteBean.getSiteOwnerId()).get());
		}
		
		return site;
	}

	private Address TransformToAddress(SiteBean siteBean) {
		Address address;
		if(siteBean.getAddress()!=null && siteBean.getAddress().getId()!=null) {
			address=addressService.findById(siteBean.getAddress().getId());
		}
		else {
			address=new Address();
		}
		address.setName(siteBean.getAddress().getName());
		address.setNumber(siteBean.getAddress().getNumber());
		address.setAddressLine1(siteBean.getAddress().getAddressLine1());
		address.setAddressLine2(siteBean.getAddress().getAddressLine2());
		address.setAddressLine3(siteBean.getAddress().getAddressLine3());
		address.setVillage(siteBean.getAddress().getVillage());
		address.setCity(siteBean.getAddress().getCity());
		address.setState(siteBean.getAddress().getState());
		address.setCountry(siteBean.getAddress().getCountry());
		address.setZipCode(siteBean.getAddress().getZipCode());
		address.setAddressType(AddressType.CURRENT);
		
		return address;
	}

	@Override
	public List<Site> findAll() {
		return siteRepository.findAll();
	}

	@Override
	public Site updates(SiteBean siteBean) {
		Site transformToSite = transformToSite(siteBean);
		return siteRepository.save(transformToSite);
	}

//	@Override
//	public List<Dealership> userDealerships(Long userId) {
//		return dealershipRepository.userDealerships(userId);
//	}

}
