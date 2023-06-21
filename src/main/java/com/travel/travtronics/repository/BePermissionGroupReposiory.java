package com.travel.travtronics.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.travel.travtronics.model.BePermissionGroupModel;

public interface BePermissionGroupReposiory extends PagingAndSortingRepository<BePermissionGroupModel, Integer> {

	BePermissionGroupModel findByGroupId(Integer groupId);

	Set<BePermissionGroupModel> findByGroupNameContainsIgnoreCase(String gname);

	Boolean existsByGroupName(String groupName);

	@Query("select group from BePermissionGroupModel group where group.orgId=?1 or 0=?1")
	Set<BePermissionGroupModel> findAllGroupInfo(Long orgId);

}
