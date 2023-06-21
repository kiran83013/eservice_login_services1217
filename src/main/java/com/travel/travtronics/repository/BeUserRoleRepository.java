package com.travel.travtronics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.model.BeUserRoleModel;

public interface BeUserRoleRepository extends PagingAndSortingRepository<BeUserRoleModel, Long> {

	@Query("select userRole from BeUserRoleModel userRole where userRole.id=:roleId")
	Optional<BeUserRoleModel> findByRoleId(@Param("roleId") Long roleId);

	@Query(value = "SELECT role.* FROM e_services_be_user.a3m_acl_role role\r\n"
			+ "INNER JOIN e_services_be_user.a3m_rel_account_role rel ON rel.role_id=role.id\r\n"
			+ "WHERE rel.account_id=?1", nativeQuery = true)
	List<BeUserRoleModel> getRoleInfo(Integer userId);

	@Query("select role.id from BeUserRoleModel role where role.name=?1")
	Optional<Long> getRoleIdByRoleName(String name);

	Optional<BeUserRoleModel> findByNameIgnoreCase(String role);

	@Query("select role  from BeUserRoleModel role where role.orgId=?1 or 0=?1")
	List<BeUserRoleModel> findAllRoleInfo(Long orgId);
}
