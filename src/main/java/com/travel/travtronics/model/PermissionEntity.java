package com.travel.travtronics.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

@Immutable
@Subselect(value = "SELECT *,\r\n"
		+ "(SELECT GROUP_CONCAT(role.name ORDER BY rel.AUTOID) FROM `a3m_rel_role_permission` rel \r\n"
		+ "JOIN `a3m_acl_role` role ON role.`id`=rel.`role_id`\r\n" + "WHERE rel.`permission_id`=p.`id`) AS roles\r\n"
		+ "FROM `a3m_acl_permission` p")
@Entity
@NamedNativeQuery(name = "PermissionEntity.getUserInfo", resultClass = UserExtraModel.class, resultSetMapping = "getUserInfo", query = "SELECT\r\n"
		+ "ud.fullname,\r\n" + "mo.Name AS org_name,\r\n" + "CONCAT(emp.FirstName,' ',emp.LastName) AS emp_name,\r\n"
		+ "CAST(CONCAT('[',\r\n"
		+ "                  (SELECT  GROUP_CONCAT(json_object('menuId',menu_id,'menuPosition',`name`))\r\n"
		+ "                                FROM `e_services`.master_org_menu \r\n"
		+ "                      WHERE org_id = u.`org_id`)           \r\n"
		+ "                                            ,     \r\n" + "                 ']') AS JSON) AS menu \r\n"
		+ " FROM \r\n" + "`e_services_be_user`.a3m_account u \r\n"
		+ "LEFT JOIN `e_services_be_user`.a3m_account_details ud ON ud.user_id=u.id\r\n"
		+ "LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = u.org_id\r\n"
		+ "LEFT JOIN e_services.emp_profile emp ON emp.ID=u.employee_id\r\n" + "WHERE \r\n" + "u.id=:id")
@SqlResultSetMapping(name = "getUserInfo", classes = @ConstructorResult(targetClass = UserExtraModel.class, columns = {
		@ColumnResult(name = "fullname", type = String.class), @ColumnResult(name = "org_name", type = String.class),
		@ColumnResult(name = "emp_name", type = String.class), @ColumnResult(name = "menu", type = String.class) }))
public class PermissionEntity {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "`key`")
	private String key;

	@Column(name = "`name`")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "group_id")
	private Integer groupId;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "created_by")
	private Integer createdBy;

	// @CreationTimestamp
	@Column(name = "date_created")
	private Date dateCreated;

	@Column(name = "updated_by")
	private Integer updatedBy;

	// @UpdateTimestamp
	@Column(name = "date_updated")
	private Date dateUpdated;

	@Column(name = "suspendedon")
	private Date suspendedOn;

	@Column(name = "is_system")
	private Integer isSystem;

	@Column(name = "roles")
	private Set<String> roles;

	@Column(name = "organization_id")
	private Long orgId;

	public Integer getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public Date getSuspendedOn() {
		return suspendedOn;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public Long getOrgId() {
		return orgId;
	}



	
	
}
