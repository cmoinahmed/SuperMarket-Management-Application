package com.ty.supermarketappspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.supermarketappspringboot.dao.UserDao;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.User;
import com.ty.supermarketappspringboot.exception.DataNotFoundException;
import com.ty.supermarketappspringboot.exception.EmailIdNotFoundException;
import com.ty.supermarketappspringboot.exception.IdNotFoundException;
import com.ty.supermarketappspringboot.exception.InvalidCredentialException;
import com.ty.supermarketappspringboot.exception.PhoneNumberNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder encoder;

	public ResponseEntity<ResponseStructure<User>> saveAdmin(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		user.setPassword(encoder.encode(user.getPassword()));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Saved Successfully");
		user.setRole("ROLE_ADMIN");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> saveBranchAdmin(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		user.setPassword(encoder.encode(user.getPassword()));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Saved Successfully");
		user.setRole("ROLE_BRANCH-ADMIN");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> saveStaff(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		user.setPassword(encoder.encode(user.getPassword()));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Saved Successfully");
		user.setRole("ROLE_STAFF");
		responseStructure.setData(userDao.saveUser(user));
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> getAdminById(String id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user = userDao.getAdminById(id);
		if (user != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Admin Fetched by Id " + id + " Successfully");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Admin Id " + id + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> getStaffById(String id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user = userDao.getStaffById(id);
		if (user != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Staff Fetched by Id " + id + " Successfully");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Staff Id " + id + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> getBranchAdminById(String id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user = userDao.getBranchAdminById(id);
		if (user != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Branch-Admin Fetched by Id " + id + " Successfully");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Branch-Admin Id " + id + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllAdmin() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		List<User> list = userDao.getAllAdmin();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Admin Fetched");
			responseStructure.setData(list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Admin Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllStaff() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		List<User> list = userDao.getAllStaff();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Staff Fetched");
			responseStructure.setData(list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Staff Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllBranchAdmin() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		List<User> list = userDao.getAllBranchAdmin();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Branch-Admin Fetched");
			responseStructure.setData(list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Branch-Admin Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteAdminById(String id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user = userDao.getAdminById(id);
		if (user != null) {
			userDao.deleteUser(user);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Admin Of Id " + id + " Data Deleted");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Admin Id " + id + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteStaffById(String id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user = userDao.getStaffById(id);
		if (user != null) {
			userDao.deleteUser(user);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Staff Of Id " + id + " Data Deleted");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Staff Id " + id + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteBranchAdminById(String id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user = userDao.getBranchAdminById(id);
		if (user != null) {
			userDao.deleteUser(user);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Branch-Admin Of Id " + id + " Data Deleted");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Branch-Admin Id " + id + "Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateAdmin(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user1 = userDao.getAdminById(user.getId());
		if (user1 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Admin Updated Successfully");
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole("ROLE_ADMIN");
			responseStructure.setData(userDao.saveUser(user));
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Admin Id " + user.getId() + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateStaff(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user1 = userDao.getStaffById(user.getId());
		if (user1 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Staff Updated Successfully");
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole("ROLE_STAFF");
			responseStructure.setData(userDao.saveUser(user));
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Staff Id " + user.getId() + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateBranchAdmin(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user1 = userDao.getBranchAdminById(user.getId());
		if (user1 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Branch-Admin Updated Successfully");
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole("ROLE_BRANCH-ADMIN");
			responseStructure.setData(userDao.saveUser(user));
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Branch-Admin Id " + user.getId() + " Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getUserByRole() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		List<User> list = userDao.getUserByRole();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User By Role Fetched Successfully");
			responseStructure.setData(list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("User Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<User>> validateUserByEmail(String email, String password) {
		User user = userDao.getUserByEmail(email);

		if (user != null) {
			if (encoder.matches(password, user.getPassword())) {
				ResponseStructure<User> responseStructure = new ResponseStructure<User>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Valid User");
				responseStructure.setData(user);
				return new ResponseEntity<>(responseStructure, HttpStatus.OK);

			} else {
				throw new InvalidCredentialException("Invalid Password");
			}
		} else {
			throw new EmailIdNotFoundException("User-Email : " + email + ", NOT Found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> validateUserByPhone(String phone, String password) {
		User user = userDao.getUserByPhone(phone);

		if (user != null) {
			if (encoder.matches(password, user.getPassword())) {
				ResponseStructure<User> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Valid User");
				responseStructure.setData(user);
				return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidCredentialException("Invalid Credential");
			}
		} else {
			throw new PhoneNumberNotFoundException("User-PhoneNumber : " + phone + ", NOT Found");
		}
	}
}