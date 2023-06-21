package com.travel.travtronics.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.model.BePermissionModel;

public interface BePermissionModelRepository extends PagingAndSortingRepository<BePermissionModel, Integer> {

	@Query("select permission from BePermissionModel permission where permission.id= :permissionId")
	Optional<BePermissionModel> findByPermissionId(@Param("permissionId") Integer permissionId);

	List<BePermissionModel> findAllByOrganizationId(Long organizationId);

	Page<BePermissionModel> findByOrganizationId(Long organizationId, Pageable pageable);

	Boolean existsByKey(String key);
	
//	Set<BePermissionModel> findByKeyContainsIgnoreCase(String key);

	Set<BePermissionModel> findByKeyIgnoreCaseEquals(String key);
}
