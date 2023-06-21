package com.travel.travtronics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.model.PermissionEntity;
import com.travel.travtronics.model.UserExtraModel;

public interface PermissionEntityRepository extends CrudRepository<PermissionEntity, Integer> {

	@Query(nativeQuery = true)
	UserExtraModel getUserInfo(@Param("id") Integer id);

	@Query("select p from PermissionEntity p where p.orgId=?1 or 0=?1")
	List<PermissionEntity> findAllPermissions(Long orgId);

}
