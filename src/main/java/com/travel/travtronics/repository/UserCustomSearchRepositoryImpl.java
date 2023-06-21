package com.travel.travtronics.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.dto.UserSearchResponse;

public class UserCustomSearchRepositoryImpl implements UserCustomSearchRepository {
	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	@Override
	public List<UserSearchResponse> findBySearchParameters(UserSearchRequestDto model) {

		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT loguser.id AS userId,\r\n" + "loguser.employee_id AS employeeId,\r\n"
				+ "CONCAT(emp.FirstName,' ',emp.LastName) AS employeeName,\r\n"
				+ "loguser.org_id AS orgId,org.Name AS orgName,\r\n" + "userdet.fullname AS userName,\r\n"
				+ "loguser.email,loguser.email_secondary AS emailSecondary,\r\n"
				+ "loguser.phone_primary AS phoneNumber,\r\n" + "userdet.phonecode AS phoneCode,\r\n"
				+ "loguser.phone_secondary AS phoneSecondary,\r\n" + "emp.BusinessUnitId AS buId,\r\n"
				+ "bu.Name AS buName,\r\n" + "userdet.location_id AS locationId,\r\n"
				+ "userdet.locationvalue AS locationValue,\r\n" + "emp.DesignationId AS designationId,\r\n"
				+ "emp.DesignationName AS designationName,\r\n" + "role.key AS roleKey,\r\n"
				+ "role.name AS roleName,\r\n" + "rel.role_id AS roleId,\r\n"
				+ "emp.DepartmentId AS departmentId,dept.Name AS departmentName,\r\n" + "loguser.status,\r\n"
				+ "loguser.createdon AS createdDate,\r\n" + "loguser.start_date AS startDate,\r\n"
				+ "loc.CostCenterId AS ccId,cc.Name AS ccName, \r\n" + "shift.start_time AS shiftFrom,\r\n"
				+ "shift.end_time AS shiftTo,shift.shift_id AS shiftId,\r\n" + "shift.shift_name AS shiftName,\r\n"
				+ "shift.timie_zone AS timeZone,\r\n" + "tz.TimeZone AS timeZoneName,\r\n"
				+ "usershift.id AS userShiftAssignmentId\r\n" + "FROM e_services_be_user.a3m_account loguser\r\n"
				+ "INNER JOIN e_services_be_user.a3m_account_details userdet ON userdet.user_id=loguser.id\r\n"
				+ "LEFT JOIN e_services.emp_profile emp ON emp.ID=loguser.employee_id\r\n"
				+ "LEFT JOIN  e_services.master_organization org ON org.OrganizationId=loguser.org_id\r\n"
				+ "LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId=emp.BusinessUnitId\r\n" + "\r\n"
				+ "INNER JOIN e_services_be_user.a3m_rel_account_role rel ON rel.account_id=loguser.id\r\n" + "\r\n"
				+ "INNER JOIN e_services_be_user.a3m_acl_role role ON role.id=rel.role_id\r\n" + "\r\n"
				+ "LEFT JOIN e_services.master_departments dept ON dept.DepartmentId=emp.DepartmentId\r\n" + "\r\n"
				+ "LEFT JOIN e_services.location  loc ON loc.LocationId=userdet.location_id\r\n" + "\r\n"
				+ "LEFT JOIN e_services.user_shift_assignments usershift ON usershift.user_id=loguser.id\r\n" + "\r\n"
				+ "LEFT JOIN e_services.shifts_manager shift ON shift.shift_id=usershift.shift_id\r\n" + "\r\n"
				+ "LEFT JOIN e_services.master_timezone tz ON tz.ID=shift.timie_zone\r\n" + "\r\n"
				+ "LEFT JOIN e_services.cost_center cc ON cc.CostCenterId=loc.CostCenterId\r\n" + "WHERE 1=1"
				+ System.lineSeparator());

		if (model.getOrgId() != null && model.getOrgId() != 0) {
			sql.append("AND loguser.org_id= :orgId" + System.lineSeparator());
			params.put("orgId", model.getOrgId());
		}
		if (model.getBuId() != null && model.getBuId() != 0) {
			sql.append("AND emp.BusinessUnitId= :buId" + System.lineSeparator());
			params.put("buId", model.getBuId());
		}
		if (model.getLocId() != null && model.getLocId() != 0) {
			sql.append("AND userdet.location_id= :locId" + System.lineSeparator());
			params.put("locId", model.getLocId());
		}
		if (model.getEmpId() != null && model.getEmpId() != 0) {
			sql.append("AND loguser.employee_id= :empId" + System.lineSeparator());
			params.put("empId", model.getEmpId());
		}

		if (model.getDesigantionId() != null && model.getDesigantionId() != 0) {
			sql.append("AND emp.DesignationId= :desigantionId" + System.lineSeparator());
			params.put("desigantionId", model.getDesigantionId());
		}
		if (model.getDeptId() != null && model.getDeptId() != 0) {
			sql.append("AND emp.DepartmentId= :deptId" + System.lineSeparator());
			params.put("deptId", model.getDeptId());
		}
		if (model.getRoleId() != null && model.getRoleId() != 0) {
			sql.append("AND rel.role_id= :roleId" + System.lineSeparator());
			params.put("roleId", model.getRoleId());
		}
		if (model.getEmail() != null && !model.getEmail().trim().isEmpty()) {
			sql.append("AND loguser.email= :email" + System.lineSeparator());
			params.put("email", model.getEmail());
		}
		if (model.getPhoneNo() != null && !model.getPhoneNo().trim().isEmpty()) {
			sql.append("AND loguser.phone_primary= :phoneNo" + System.lineSeparator());
			params.put("phoneNo", model.getPhoneNo());
		}
		if (model.getCcId() != null && model.getCcId() != 0) {
			sql.append("AND loc.CostCenterId= :ccId" + System.lineSeparator());
			params.put("ccId", model.getCcId());
		}
		if (model.getShiftFrom() != null && !model.getShiftFrom().trim().isBlank()) {
			sql.append("AND shift.start_time= :shiftFrom" + System.lineSeparator());
			params.put("shiftFrom", model.getShiftFrom());
		}

		if (model.getShiftTo() != null && !model.getShiftTo().isBlank()) {
			sql.append("AND shift.end_time= :shiftTo" + System.lineSeparator());
			params.put("shiftTo", model.getShiftTo());
		}

		if (model.getShiftId() != null && model.getShiftId() != 0) {

			sql.append("AND usershift.shift_id= :shiftId" + System.lineSeparator());
			params.put("shiftId", model.getShiftId());
		}
		if (model.getTimeZone() != null && !model.getTimeZone().isBlank()) {
			sql.append("AND shift.timie_zone= :timeZone" + System.lineSeparator());
			params.put("timeZone", model.getTimeZone());
		}
		Query query = entityManager.createNativeQuery(sql.toString(), "find_by_search_params");

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}

		@SuppressWarnings("unchecked")
		List<UserSearchResponse> resultList = query.getResultList();

		return resultList;

	}

}
