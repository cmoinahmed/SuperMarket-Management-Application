package com.ty.supermarketappspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.supermarketappspringboot.dto.User;
import com.ty.supermarketappspringboot.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getAdminById(String id) {
		User user = userRepository.getAdminById(id);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public List<User> getAllAdmin() {
		return userRepository.getAllAdmin();
	}

	public User getStaffById(String id) {
		User user = userRepository.getStaffById(id);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public List<User> getAllStaff() {
		return userRepository.getAllStaff();
	}

	public User getBranchAdminById(String id) {
		User user = userRepository.getBranchAdminById(id);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public List<User> getAllBranchAdmin() {
		return userRepository.getAllBranchAdmin();
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public User getUserByPhone(String phone) {
		Optional<User> optional = userRepository.findByPhone(phone);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public User getUserByEmail(String email) {
		Optional<User> optional = userRepository.findByEmail(email);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<User> getUserByRole() {
		return userRepository.getUserByRole();
	}
}
