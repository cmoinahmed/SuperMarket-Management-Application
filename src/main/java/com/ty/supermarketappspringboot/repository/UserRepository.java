package com.ty.supermarketappspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.supermarketappspringboot.dto.User;

public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findByEmail(String email);

	public Optional<User> findByPhone(String phone);

	public Optional<User> findByUserName(String username);

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role LIKE 'ROLE_ADMIN'"))
	public List<User> getAllAdmin();

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role LIKE 'ROLE_ADMIN' and id = ?"))
	public User getAdminById(String id);

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role LIKE 'ROLE_STAFF'"))
	public List<User> getAllStaff();

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role LIKE 'ROLE_STAFF' and id = ?"))
	public User getStaffById(String id);

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role LIKE 'ROLE_BRANCH-ADMIN'"))
	public List<User> getAllBranchAdmin();

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role LIKE 'ROLE_BRANCH-ADMIN' and id = ?"))
	public User getBranchAdminById(String id);

	@Query(nativeQuery = true, value = ("select *,0 as clazz from user u where u.role between 'ROLE_BRANCH-ADMIN' and 'ROLE_STAFF'"))
	public List<User> getUserByRole();

}