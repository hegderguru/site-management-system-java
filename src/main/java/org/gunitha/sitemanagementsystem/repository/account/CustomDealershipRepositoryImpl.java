package org.gunitha.sitemanagementsystem.repository.account;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.gunitha.sitemanagementsystem.model.account.Dealership;
import org.gunitha.sitemanagementsystem.model.account.Site;
import org.gunitha.sitemanagementsystem.model.user.DealerUser;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDealershipRepositoryImpl implements CustomDealershipRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Dealership> userDealerships(Long userId) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Dealership> criteriaQuery = criteriaBuilder.createQuery(Dealership.class);

		Root<DealerUser> rootDu = criteriaQuery.from(DealerUser.class);
		Root<Dealership> rootD = criteriaQuery.from(Dealership.class);

		criteriaQuery.multiselect(rootD, rootDu);

		Predicate userRestriction = criteriaBuilder.and(criteriaBuilder.equal(rootDu.get("id"), userId));

		criteriaQuery.where(criteriaBuilder.and(userRestriction));

		TypedQuery<Dealership> createQuery = entityManager.createQuery(criteriaQuery);

		return createQuery.getResultList();
	}

	@Override
	public List<Dealership> dealershipsStartsWithName(String name) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Dealership> createQuery = criteriaBuilder.createQuery(Dealership.class);
		
		Root<Dealership> from = createQuery.from(Dealership.class);
		
		createQuery.select(from);
		
		Predicate predicate = criteriaBuilder.like(from.get("name"), "%"+name+"%");
		
		createQuery.where(predicate);		
		
		return entityManager.createQuery(createQuery).getResultList();
	}

	@Override
	public List<Dealership> findBySiteId(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Dealership> criteriaQuery = criteriaBuilder.createQuery(Dealership.class);

		Root<Site> rootSite = criteriaQuery.from(Site.class);
		Root<Dealership> rootD = criteriaQuery.from(Dealership.class);

		criteriaQuery.multiselect(rootD, rootSite);

		Predicate siteRestriction = criteriaBuilder.and(criteriaBuilder.equal(rootSite.get("id"), id));

		criteriaQuery.where(criteriaBuilder.and(siteRestriction));

		TypedQuery<Dealership> createQuery = entityManager.createQuery(criteriaQuery);

		return createQuery.getResultList();
	}
}
