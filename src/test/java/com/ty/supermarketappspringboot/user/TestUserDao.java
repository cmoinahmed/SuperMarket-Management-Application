package com.ty.supermarketappspringboot.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ty.supermarketappspringboot.dao.UserDao;
import com.ty.supermarketappspringboot.dto.User;
import com.ty.supermarketappspringboot.repository.UserRepository;

@SpringBootTest
public class TestUserDao {

	@MockBean
	UserRepository userRepository;

	@Autowired
	UserDao userDao;

	static Optional<User> optional;

	@BeforeAll
	public static void set() {
		User user = new User("User-101", "Moin", "cmoinahmed@gmail.com", "8618227093", "cmoin", "ROLE_ADMIN");
		optional = Optional.of(user);
	}

	@Test
	public void testSaveUser() {
		User user = optional.get();
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userDao.saveUser(user));
	}

	@Test
	public void testGetAdminById() {
		when(userRepository.getAdminById("User-101")).thenReturn(optional.get());
		assertEquals(optional.get(), userDao.getAdminById("User-101"));
	}

	@Test
	public void testGetAllAdmins() {
		List<User> users = new ArrayList<>();
		users.add(optional.get());
		users.add(optional.get());

		when(userRepository.getAllAdmin()).thenReturn(users);
		assertEquals(users, userDao.getAllAdmin());
	}

	@Test
	public void testGetStaffById() {
		when(userRepository.getStaffById("User-102")).thenReturn(optional.get());
		assertEquals(optional.get(), userDao.getStaffById("User-102"));
	}

	@Test
	public void testGetAllStaffs() {
		List<User> users = new ArrayList<>();
		users.add(optional.get());
		users.add(optional.get());

		when(userRepository.getAllStaff()).thenReturn(users);
		assertEquals(users, userDao.getAllStaff());
	}

	@Test
	public void testGetBranchAdminById() {
		when(userRepository.getBranchAdminById("User-103")).thenReturn(optional.get());
		assertEquals(optional.get(), userDao.getBranchAdminById("User-103"));
	}

	@Test
	public void testGetAllBranchAdmins() {
		List<User> users = new ArrayList<>();
		users.add(optional.get());
		users.add(optional.get());

		when(userRepository.getAllBranchAdmin()).thenReturn(users);
		assertEquals(users, userDao.getAllBranchAdmin());
	}

	@Test
	public void testGetUserByEmail() {
		when(userRepository.findByEmail("cmoinahmed@gmail.com")).thenReturn(optional);
		assertEquals(optional.get(), userDao.getUserByEmail("cmoinahmed@gmail.com"));
	}

	@Test
	public void testGetUserByPhone() {
		when(userRepository.findByPhone("8618227093")).thenReturn(optional);
		assertEquals(optional.get(), userDao.getUserByPhone("8618227093"));
	}

	@Test
	public void testGetUserByRole() {
		List<User> users = new ArrayList<>();
		users.add(optional.get());
		users.add(optional.get());
		
		when(userRepository.getUserByRole()).thenReturn(users);
		assertEquals(users, userDao.getUserByRole());
	}
	
}
