package com.travel.travtronics.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.model.BeRolePermissionRelation;

public interface BeRolePermissionRelationRepository extends CrudRepository<BeRolePermissionRelation, Integer> {

	List<BeRolePermissionRelation> findByRoleIdIn(List<Integer> roleIds);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM `a3m_rel_role_permission` WHERE `permission_id`=?1", nativeQuery = true)
	int deleteByPermission(Integer permissionId);

	Set<BeRolePermissionRelation> findByPermissionId(Integer permissionId);
}
