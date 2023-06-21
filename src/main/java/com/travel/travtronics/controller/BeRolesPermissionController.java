package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.dto.PermissionRequestModel;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.model.BePermissionGroupModel;
import com.travel.travtronics.model.BeUserRoleModel;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.BeRolePermissionService;

@RestController
public class BeRolesPermissionController {

	@Autowired
	BeRolePermissionService rolePermissionService;

	@PostMapping(value = "/add-be-permission")
	public ResponseEntity<?> createBePermissions(@RequestBody List<PermissionRequestModel> model) {
		return rolePermissionService.createBePermissions(model);
	}

	@GetMapping(value = "/get-be-permission")
	public ResponseEntity<?> getBePermission(@RequestParam Integer permissionId) {
		return rolePermissionService.getBePermission(permissionId);
	}

	@GetMapping(value = "/get-be-permissions")
	public ResponseEntity<?> getBePermissionsList(@RequestParam(required = false) Long orgId) {
		return rolePermissionService.getBePermissionList(orgId);
	}

	@PutMapping(value = "/modify-be-permission")
	public ResponseEntity<?> modifyBePermission(@RequestBody PermissionRequestModel model) {
		return rolePermissionService.modifyBePermission(model);
	}

	@PostMapping(value = "/add-be-permission-groups")
	public ResponseEntity<?> createBePermissionGroup(@RequestBody List<BePermissionGroupModel> models) {
		return rolePermissionService.createBePermissionGroup(models);
	}

	@GetMapping(value = "/get-be-permission-group")
	public ResponseEntity<?> getBePermissionGroup(@RequestParam Integer groupId) {
		return rolePermissionService.getBePermissionGroup(groupId);
	}

	@GetMapping(value = "/get-be-permission-groups")
	public ResponseEntity<?> getBePermissionGroupList(@RequestParam(required = false) Long orgId) {
		return rolePermissionService.getBePermissionGroupList(orgId);
	}

	@PutMapping(value = "/modify-be-permission-groups")
	public ResponseEntity<?> modifyBePermissionGroup(@RequestBody BePermissionGroupModel model) {
		return rolePermissionService.modifyBePermissionGroup(model);
	}

	@PostMapping(value = "/add-be-user-role")
	public ResponseEntity<?> createBeUserRole(@RequestBody List<BeUserRoleModel> models) {
		return rolePermissionService.createBeUserRole(models);
	}

	@GetMapping(value = "/get-be-user-role")
	public ResponseEntity<?> getBeUserRole(@RequestParam Long roleId) {
		return rolePermissionService.getBeUserRole(roleId);
	}

	@GetMapping(value = "/get-be-user-roles")
	public ResponseEntity<?> getBeUserRoles(@RequestParam(required = false) Long orgId) {
		return rolePermissionService.getBeUserRoles(orgId);
	}

	@PutMapping(value = "/modify-be-user-role")
	public ResponseEntity<?> modifyBeUserRole(@RequestBody BeUserRoleModel model) {
		return rolePermissionService.modifyBeUserRole(model);
	}

	@PostMapping(value = "/add-be-user-role-relation")
	public ResponseEntity<?> createUserRoleRelation(@RequestParam Integer userId, @RequestParam Integer roleId) {
		return rolePermissionService.createUserRoleRelation(userId, roleId);
	}

//	@PostMapping(value = "/add-be-permission-role-relation")
//	public ResponseEntity<?> createPermissionRoleRelation(@RequestParam Integer permissionId,
//			@RequestParam Integer roleId) {
//		return rolePermissionService.createPermissionRoleRelation(permissionId, roleId);
//	}

	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return rolePermissionService.getAll(organizationId);
	}

	@GetMapping(value = "/list-berole-permision-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return rolePermissionService.getPagenationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}

	@GetMapping(value = "/search-group")
	public ResponseEntity<?> searchGroup(@RequestParam String groupName) {
		return rolePermissionService.searchGroup(groupName);
	}

	@GetMapping(value = "/search-permission")
	public ResponseEntity<?> searchPermission(@RequestParam String key) {
		return rolePermissionService.searchPermission(key);
	}
}
