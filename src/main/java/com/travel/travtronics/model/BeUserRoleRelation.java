package com.travel.travtronics.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.travel.travtronics.dto.UserSearchResponse;

@Entity
@Table(name = "a3m_rel_account_role")
@SqlResultSetMapping(name = "find_by_search_params", classes = @ConstructorResult(targetClass = UserSearchResponse.class, columns = {
@ColumnResult(name = "userId", type = Integer.class), @ColumnResult(name = "employeeId", type = Integer.class),
@ColumnResult(name = "employeeName", type = String.class), @ColumnResult(name = "orgId", type = Integer.class),
@ColumnResult(name = "orgName", type = String.class), @ColumnResult(name = "userName", type = String.class),
@ColumnResult(name = "email", type = String.class), @ColumnResult(name = "emailSecondary", type = String.class),
@ColumnResult(name = "phoneNumber", type = String.class),@ColumnResult(name = "phoneCode", type = String.class),
@ColumnResult(name = "phoneSecondary", type = String.class), @ColumnResult(name = "buId", type = Integer.class),
@ColumnResult(name = "buName", type = String.class), @ColumnResult(name = "locationId", type = Integer.class),
@ColumnResult(name = "locationValue", type = String.class),@ColumnResult(name = "designationId", type = Integer.class),
@ColumnResult(name = "designationName", type = String.class),@ColumnResult(name = "roleKey", type = String.class), 
@ColumnResult(name = "roleName", type = String.class),@ColumnResult(name = "roleId", type = Integer.class),
@ColumnResult(name = "departmentId", type = Integer.class),@ColumnResult(name = "departmentName", type = String.class),
@ColumnResult(name = "status", type = String.class), @ColumnResult(name = "createdDate", type = String.class),
@ColumnResult(name = "startDate", type = String.class), @ColumnResult(name = "ccId", type = Integer.class),
@ColumnResult(name = "ccName", type = String.class),@ColumnResult(name = "shiftFrom", type = String.class),
@ColumnResult(name = "shiftTo", type = String.class),@ColumnResult(name = "shiftId", type = Long.class),
@ColumnResult(name = "shiftName", type = String.class),@ColumnResult(name = "timeZone", type = String.class),
@ColumnResult(name = "timeZoneName", type = String.class),@ColumnResult(name = "userShiftAssignmentId", type = Long.class)}))
public class BeUserRoleRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTOID")
	private Integer id;

	@Column(name = "account_id")
	private Integer userId;

	@Column(name = "role_id")
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
