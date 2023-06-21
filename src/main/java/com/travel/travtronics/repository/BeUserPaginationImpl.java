package com.travel.travtronics.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.model.BeUserModel;

@Component
public class BeUserPaginationImpl implements BeUserCustomPaginationRepository{
	
	@PersistenceContext
	@Autowired
	EntityManager em;

	
	@Override
	public Page<BeUserModel> fetchPagination(Integer orgId, String userName, Pageable pageable, String sortBy,
			SortType sortType) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BeUserModel> createQuery = builder.createQuery(BeUserModel.class);
		Root<BeUserModel> root = createQuery.from(BeUserModel.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (orgId != null && orgId != 0) {
			predicates.add(builder.equal(root.<Long>get("orgId"), orgId));
		}
		if (userName != null && !userName.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("userName")),
					"%" + userName.toLowerCase() + "%"));

		}
		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.asc(root.get(sortBy)));
		}
		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.desc(root.get(sortBy)));
		}
		List<BeUserModel> resultList = em.createQuery(createQuery).setFirstResult(Math.toIntExact(pageable.getOffset()))
				.setMaxResults(pageable.getPageSize()).getResultList();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<BeUserModel> serviceRootCount = countQuery.from(BeUserModel.class);
		countQuery.select(builder.count(serviceRootCount))
		.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		Long count = em.createQuery(countQuery).getSingleResult();
		Page<BeUserModel> finalList = new PageImpl<>(resultList, pageable, count);
		return finalList;
	}

}
