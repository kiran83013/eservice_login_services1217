package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.dto.ApiConverter;
import com.travel.travtronics.dto.PermissionRequestModel;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.model.BePermissionGroupModel;
import com.travel.travtronics.model.BePermissionModel;
import com.travel.travtronics.model.BeRolePermissionRelation;
import com.travel.travtronics.model.BeUserRoleModel;
import com.travel.travtronics.model.BeUserRoleRelation;
import com.travel.travtronics.repository.BePermissionGroupReposiory;
import com.travel.travtronics.repository.BePermissionModelRepository;
import com.travel.travtronics.repository.BeRolePermissionRelationRepository;
import com.travel.travtronics.repository.BeUserRoleRelationRepository;
import com.travel.travtronics.repository.BeUserRoleRepository;
import com.travel.travtronics.repository.PermissionEntityRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;

@Service
public class BeRolePermissionService {

	@Autowired
	BePermissionGroupReposiory permissionGroupRepository;

	@Autowired
	BePermissionModelRepository permissionModelRepository;

	@Autowired
	BeUserRoleRepository beUserRoleRepository;

	@Autowired
	BeUserRoleRelationRepository roleUserRelationRepository;

	@Autowired
	BeRolePermissionRelationRepository rolePermissionRepository;

	@Autowired
	PermissionEntityRepository permissionEntityRepository;

	/*
	 * backend permissons apis
	 */

	public ResponseEntity<?> createBePermissions(List<PermissionRequestModel> models) {

//		models.stream().forEach(model -> {
//			BePermissionGroupModel groupModel = permissionGroupRepository
//					.findByGroupId(model.getGroupId() != null && model.getGroupId() != 0 ? model.getGroupId() : 0);
//			if (Objects.nonNull(model)) {
//				model.setGroupName(groupModel.getGroupName());
//				permissionModelRepository.save(ApiConverter.mapPermissonRequestToModel(model));
//			} else {
//				/*
//				 * create new group
//				 */
//
//				BePermissionGroupModel createNewGroups = createNewGroups(model.getGroupName(), model.getParentId());
//				BePermissionModel permissionMOdel = ApiConverter.mapPermissonRequestToModel(model);
//				permissionMOdel.setGroupId(createNewGroups.getGroupId());
//				permissionModelRepository.save(permissionMOdel);
//			}
//		});

//		models.stream().forEach(model -> {
//
//			BePermissionModel savedPermission = permissionModelRepository
//					.save(ApiConverter.mapPermissonRequestToModel(model));
//
//			if (Objects.nonNull(model.getRoles()) && !model.getRoles().isEmpty()) {
//				model.getRoles().forEach(r -> {
//
//					Optional<BeUserRoleModel> roleInfo = beUserRoleRepository.findByNameIgnoreCase(r);
//					if (roleInfo.isPresent())
//						createPermissionRoleRelation(savedPermission.getId(), roleInfo.get().getId().intValue());
//				});
//			}
//
//		});

		for (PermissionRequestModel model : models) {
			if (permissionModelRepository.existsByKey(model.getKey())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"duplicate key exists\"}");
			}

			BePermissionModel savedPermission = permissionModelRepository
					.save(ApiConverter.mapPermissonRequestToModel(model));

			if (Objects.nonNull(model.getRoles()) && !model.getRoles().isEmpty()) {
				model.getRoles().forEach(r -> {

					Optional<BeUserRoleModel> roleInfo = beUserRoleRepository.findByNameIgnoreCase(r);
					if (roleInfo.isPresent())
						createPermissionRoleRelation(savedPermission.getId(), roleInfo.get().getId().intValue());
				});
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"permission-created\"}");
	}

	public BePermissionGroupModel createNewGroups(String groupName, Integer parentId) {
		BePermissionGroupModel groupModel = new BePermissionGroupModel();
		groupModel.setGroupName(groupName);
		groupModel.setParentId(parentId != null ? parentId : null);
		BePermissionGroupModel save = permissionGroupRepository.save(groupModel);
		return save;

	}

	public ResponseEntity<?> getBePermission(Integer permissionId) {

		Optional<BePermissionModel> permissionValidation = permissionModelRepository.findByPermissionId(permissionId);
		if (!permissionValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-not-found\"}");
		else {
			PermissionRequestModel response = ApiConverter.mapPModelToRequest(permissionValidation.get(),
					extractRoles(permissionValidation.get().getId()));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	public Set<String> extractRoles(Integer permissionId) {

		return rolePermissionRepository.findByPermissionId(permissionId).stream().map(rel -> {

			Optional<BeUserRoleModel> role = beUserRoleRepository.findByRoleId(rel.getRoleId().longValue());
			if (role.isPresent())
				return role.get().getName();
			else
				return "";

		}).filter(r -> !r.isEmpty()).collect(Collectors.toSet());

	}

	public ResponseEntity<?> getBePermissionList(Long orgId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(permissionEntityRepository.findAllPermissions(Objects.nonNull(orgId) ? orgId : 0L));
	}

	public ResponseEntity<?> modifyBePermission(PermissionRequestModel model) {
		Optional<BePermissionModel> permissionValidation = permissionModelRepository.findByPermissionId(model.getId());
		if (!permissionValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-not-found\"}");
		else {
			BePermissionModel savedPermission = permissionModelRepository
					.save(ApiConverter.mapPermissonRequestToModel(model));
			if (Objects.nonNull(model.getRoles()) && !model.getRoles().isEmpty()) {

				rolePermissionRepository.deleteByPermission(savedPermission.getId());
				model.getRoles().forEach(r -> {

					Optional<BeUserRoleModel> roleInfo = beUserRoleRepository.findByNameIgnoreCase(r);
					if (roleInfo.isPresent())
						createPermissionRoleRelation(savedPermission.getId(), roleInfo.get().getId().intValue());
				});
			}
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"permission-modified\"}");
		}

	}

	/*
	 * backend permissons group apis
	 */
	public ResponseEntity<?> createBePermissionGroup(List<BePermissionGroupModel> models) {

		for (BePermissionGroupModel model : models) {

			if (permissionGroupRepository.existsByGroupName(model.getGroupName())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"duplicate group received\"}");
			}
			permissionGroupRepository.save(model);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"permission-groups-created\"}");
	}

	public ResponseEntity<?> getBePermissionGroup(Integer groupId) {
		BePermissionGroupModel groupValidation = permissionGroupRepository.findByGroupId(groupId);
		if (Objects.isNull(groupValidation))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-group-not-found\"}");
		else
			return ResponseEntity.status(HttpStatus.OK).body(groupValidation);

	}

	public ResponseEntity<?> getBePermissionGroupList(Long orgId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(permissionGroupRepository.findAllGroupInfo(Objects.nonNull(orgId) ? orgId : 0L));
	}

	public ResponseEntity<?> modifyBePermissionGroup(BePermissionGroupModel model) {
		BePermissionGroupModel groupValidation = permissionGroupRepository.findByGroupId(model.getGroupId());
		if (Objects.isNull(groupValidation))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-group-not-found\"}");
		else {
			permissionGroupRepository.save(model);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"permission-group-modified\"}");
		}
	}

	/*
	 * be user role apis
	 */
	public ResponseEntity<?> createBeUserRole(List<BeUserRoleModel> models) {

		models.stream().forEach(model -> {
			beUserRoleRepository.save(model);
		});

		return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"roles-created\"}");
	}

	public ResponseEntity<?> getBeUserRole(Long roleId) {
		Optional<BeUserRoleModel> roleValidation = beUserRoleRepository.findByRoleId(roleId);
		if (!roleValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"role-not-found\"}");
		else
			return ResponseEntity.status(HttpStatus.OK).body(roleValidation);
	}

	public ResponseEntity<?> getBeUserRoles(Long orgId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(beUserRoleRepository.findAllRoleInfo(Objects.nonNull(orgId) ? orgId : 0L));
	}

	public ResponseEntity<?> modifyBeUserRole(BeUserRoleModel model) {
		Optional<BeUserRoleModel> roleValidation = beUserRoleRepository.findByRoleId(model.getId());
		if (!roleValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"role-not-found\"}");
		else {
			beUserRoleRepository.save(model);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"role-modified\"}");
		}
	}
	/*
	 * user-role and role-permission-apis
	 */

	public ResponseEntity<?> createUserRoleRelation(Integer userId, Integer roleId) {
		BeUserRoleRelation relation = new BeUserRoleRelation();
		relation.setRoleId(roleId);
		relation.setUserId(userId);
		roleUserRelationRepository.save(relation);
		return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"role-user-relation-created\"}");
	}

	public ResponseEntity<?> createPermissionRoleRelation(Integer permissionId, Integer roleId) {
		BeRolePermissionRelation relation = new BeRolePermissionRelation();
		relation.setPermissionId(permissionId);
		relation.setRoleId(roleId);
		rolePermissionRepository.save(relation);
		return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"role-permission-relation-created\"}");
	}

//	public ResponseEntity<?> getBeUserRoles(Long organizationId) {
//		return ResponseEntity.status(HttpStatus.OK).body(permissionModelRepository.findAllByOrganizationId(organizationId));
//		
//	}

	public APIResponse getAll(Long organizationId) {
		try {
			List<BePermissionModel> list = permissionModelRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<BePermissionModel> findByOrganizationId = permissionModelRepository
					.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<BePermissionModel> findAll = permissionModelRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

	public ResponseEntity<?> searchGroup(String groupName) {

		Set<BePermissionGroupModel> group = permissionGroupRepository.findByGroupNameContainsIgnoreCase(groupName);
		return ResponseEntity.status(HttpStatus.OK).body(group);
	}

	public ResponseEntity<?> searchPermission(String key) {

		Set<BePermissionModel> keyData = permissionModelRepository.findByKeyIgnoreCaseEquals(key);

		return ResponseEntity.status(HttpStatus.OK).body(keyData);
	}
}
