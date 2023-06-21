package com.travel.travtronics.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.model.BeUserModel;

public interface BeUserRepository extends PagingAndSortingRepository<BeUserModel, Integer> {

	Optional<BeUserModel> findByEmailOrPhoneNumber(String email, String phoneNumber);

	BeUserModel findByEmailAndIamIdIsNotNull(String email);

	BeUserModel findByUserId(Integer userId);

	List<BeUserModel> findByEmployeeIdAndEmployeeIdIsNotNull(String empId);

//	@Query("select user from BeUserModel user where user.firstName LIKE  %?1% OR "
//			+ "user.lastName LIKE %?1% OR CONCAT(user.firstName,' ',user.lastName) LIKE  %?1%  OR user.userName LIKE %?1%")
//	List<BeUserModel> searchByUserName(String name);

	@Transactional
	@Modifying
	@Query("update BeUserModel u set u.iamId = :iamId where u.userId = :userId")
	void updateIamId(@Param("iamId") String iamId, @Param("userId") Integer userId);

	BeUserModel findByUserIdAndIamIdIsNotNull(Integer userId);

	List<BeUserModel> findByUserNameContainingIgnoreCase(String name);

	@Query(value = "SELECT CONCAT(emp.FirstName,' ',emp.LastName) AS empName  FROM e_services.emp_profile emp WHERE emp.ID='1'", nativeQuery = true)
	Optional<String> getEmpName(String Id);


	Page<BeUserModel> findByOrgId(Integer orgId, Pageable pageable);
	
//	@Query(value = "SELECT a.id AS userId,a.employee_id AS employeeId, a.org_id AS orgId, a.username AS userName, a.email AS email, a.email_secondary AS emailSecondary, a.phone_primary AS phoneNumber, a.phone_secondary AS phoneSecondary, a.createdon AS createdDate, a.verifiedon AS verifiedOn, a.lastsignedinon AS lastSignedInOn,\r\n" + 
//			"a.resetsenton AS resetSentOn, a.deletedon AS deletedOn, a.suspendedon AS suspendedOn, a.employee_booking_amount AS employeeBookingAmount, a.credit_limit_amount AS creditLimitAmount, a.running_balance AS runningBalance, a.userupdatepassword AS userUpdatePassword, a.start_date AS startDate, a.end_date AS endDate,\r\n" + 
//			"a.status AS status,\r\n" + 
//			"ad.dept_id AS departmentId,md.Name AS departmentName FROM e_services_be_user.a3m_account_details ad\r\n" + 
//			"LEFT JOIN e_services.master_departments md ON md.DepartmentId =ad.dept_id\r\n" + 
//			"LEFT JOIN e_services_be_user.a3m_account a ON a.id = ad.user_id",nativeQuery = true)
//	List<Map<String,String>> findAllList();
	
	@Query(value = "SELECT a.id AS userId,a.employee_id AS employeeId, a.org_id AS orgId, a.username AS userName, a.email AS email, a.email_secondary AS emailSecondary, a.phone_primary AS phoneNumber, a.phone_secondary AS phoneSecondary, a.createdon AS createdDate, a.verifiedon AS verifiedOn, a.lastsignedinon AS lastSignedInOn,\r\n" + 
			"a.resetsenton AS resetSentOn, a.deletedon AS deletedOn, a.suspendedon AS suspendedOn, a.employee_booking_amount AS employeeBookingAmount, a.credit_limit_amount AS creditLimitAmount, a.running_balance AS runningBalance, a.userupdatepassword AS userUpdatePassword, a.start_date AS startDate, a.end_date AS endDate,\r\n" + 
			"a.status AS status,\r\n" + 
			"ad.dept_id AS departmentId,md.Name AS departmentName FROM e_services_be_user.a3m_account_details ad\r\n" + 
			"LEFT JOIN e_services.master_departments md ON md.DepartmentId =ad.dept_id\r\n" + 
			"LEFT JOIN e_services_be_user.a3m_account a ON a.id = ad.user_id WHERE a.org_id=?1",nativeQuery = true)
	List<Map<String,String>> findByOrgId(Long orgId);
	
	@Query(value = "SELECT org.Name AS organizationName  FROM e_services.master_organization  org WHERE org.OrganizationId=?1", nativeQuery = true)
	Optional<String> getOrgName(Integer orgId);
	
	@Query(value = "SELECT a3m.username FROM a3m_account a3m WHERE a3m.username LIKE '%:?username%' ",nativeQuery = true)
	Optional<String> getUserName(String userName);

//	Optional<BeUserModel> findByEmailOrPhoneNumberOrUserName(String email, String phoneNumber, String userName);
	
	Optional<BeUserModel> findByEmail(String email);
	
	Optional<BeUserModel> findByPhoneNumber(String phoneNumber);

	Optional<BeUserModel> findByUserName(String userName);

	BeUserModel findByIamId(String iamId);

	

}//%:question%
