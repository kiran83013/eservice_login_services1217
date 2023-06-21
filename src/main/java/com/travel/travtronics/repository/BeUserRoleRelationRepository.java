package com.travel.travtronics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.model.BeUserRoleRelation;

@Repository
public interface BeUserRoleRelationRepository extends CrudRepository<BeUserRoleRelation, Integer>,UserCustomSearchRepository {

	List<BeUserRoleRelation> findAllByUserId(Integer userId);
	
	@Query(value = "SELECT org.Name AS orgName FROM e_services.master_organization  org WHERE org.OrganizationId=?1", nativeQuery = true)
	Optional<String> getOrgNameByOrgId(Integer id);

	@Query(value = "SELECT customer.BusinessName AS orgName FROM e_services.customer_info  customer WHERE customer.CUSTOMERID=?1", nativeQuery = true)
	Optional<String> getCustomerInfo(Integer id);
}
