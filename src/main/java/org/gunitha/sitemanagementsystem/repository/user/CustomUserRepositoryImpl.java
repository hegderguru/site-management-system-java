package org.gunitha.sitemanagementsystem.repository.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.gunitha.sitemanagementsystem.model.user.SiteEngineer;
import org.gunitha.sitemanagementsystem.model.user.SiteOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private DealerUserRepository dealerUserRepository;

	@Override
	public List<SiteEngineer> findAllSiteEngineersStartsWithUserName(String startsWithUserName, String username) {

		DealerUser dealerUser = dealerUserRepository.findByUsername(username);

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<SiteEngineer> createQuery = criteriaBuilder.createQuery(SiteEngineer.class);

		Root<DealerUser> fromDealerUser = createQuery.from(DealerUser.class);
		Join<DealerUser, Dealership> joinDealerUserDealerships = fromDealerUser.join("dealerships");
		Root<SiteEngineer> fromSiteEngineer = createQuery.from(SiteEngineer.class);
		Join<SiteEngineer, Dealership> joinSiteUserDealerships = fromSiteEngineer.join("dealerships");
		
		createQuery.select(fromSiteEngineer);

		createQuery.where(criteriaBuilder.like(fromDealerUser.get("username"), username),
					criteriaBuilder.and(criteriaBuilder.equal(joinDealerUserDealerships.get("id"), joinSiteUserDealerships.get("id"))));
		

		TypedQuery<SiteEngineer> createQuery2 = entityManager.createQuery(createQuery);

		return createQuery2.getResultList();
	}
	
	@Override
	public List<SiteOwner> findAllSiteOwnersStartsWithUserName(String startsWithUserName, String username) {

		DealerUser dealerUser = dealerUserRepository.findByUsername(username);

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<SiteOwner> createQuery = criteriaBuilder.createQuery(SiteOwner.class);

		Root<DealerUser> fromDealerUser = createQuery.from(DealerUser.class);
		Join<DealerUser, Dealership> joinDealerUserDealerships = fromDealerUser.join("dealerships");
		Root<SiteOwner> fromSiteOwner = createQuery.from(SiteOwner.class);
		Join<SiteOwner, Dealership> joinSiteUserDealerships = fromSiteOwner.join("dealerships");
		
		createQuery.select(fromSiteOwner);

		createQuery.where(criteriaBuilder.like(fromDealerUser.get("username"), username),
					criteriaBuilder.and(criteriaBuilder.equal(joinDealerUserDealerships.get("id"), joinSiteUserDealerships.get("id"))));
		

		TypedQuery<SiteOwner> createQuery2 = entityManager.createQuery(createQuery);

		return createQuery2.getResultList();
	}

}
