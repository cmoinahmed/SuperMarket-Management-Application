package com.ty.supermarketappspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.supermarketappspringboot.dao.SuperMarketDao;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.SuperMarket;
import com.ty.supermarketappspringboot.exception.DataNotFoundException;
import com.ty.supermarketappspringboot.exception.EmailIdNotFoundException;
import com.ty.supermarketappspringboot.exception.IdNotFoundException;
import com.ty.supermarketappspringboot.exception.InvalidCredentialException;

@Service
public class SuperMarketService {
	@Autowired
	private SuperMarketDao superMarketDao;

	@Autowired
	private PasswordEncoder encoder;

	public ResponseEntity<ResponseStructure<SuperMarket>> createSuperMarket(SuperMarket superMarket) {
		ResponseStructure<SuperMarket> responseStructure = new ResponseStructure<>();
		superMarket.setPassword(encoder.encode(superMarket.getPassword()));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("SuperMarket Created sucessfully");
		responseStructure.setData(superMarketDao.createSuperMarket(superMarket));
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<SuperMarket>> getSuperMarket(String id) {
		ResponseStructure<SuperMarket> responseStructure = new ResponseStructure<>();
		SuperMarket superMarket = superMarketDao.getSuperMarket(id);
		if (superMarket != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Super market fetched by id" + id + "successfully");
			responseStructure.setData(superMarket);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("SuperMarket id" + id + "not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<SuperMarket>>> getAllSuperMarket() {
		ResponseStructure<List<SuperMarket>> responseStructure = new ResponseStructure<>();
		List<SuperMarket> list = superMarketDao.getAllSuperMarket();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SuperMarket fetched");
			responseStructure.setData(list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("SuperMarket Data Not Present");
		}
	}

	public ResponseEntity<ResponseStructure<SuperMarket>> updateSuperMarket(String id, SuperMarket superMarket) {
		ResponseStructure<SuperMarket> responseStructure = new ResponseStructure<SuperMarket>();
		SuperMarket superMarket1 = superMarketDao.getSuperMarket(id);
		if (superMarket1 != null) {
			superMarket1.setAddress(superMarket.getAddress());
			superMarket1.setEmail(superMarket.getEmail());
			superMarket1.setPassword(encoder.encode(superMarket.getPassword()));
			superMarket1.setName(superMarket.getName());
			superMarket1.setPhone(superMarket.getPhone());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SuperMarket Updated Successfully");
			responseStructure.setData(superMarketDao.createSuperMarket(superMarket1));
			return new ResponseEntity<ResponseStructure<SuperMarket>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "SuperMarket ID " + id + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<SuperMarket>> deleteSuperMarketById(String id) {
		ResponseStructure<SuperMarket> responseStructure = new ResponseStructure<>();
		SuperMarket superMarket = superMarketDao.getSuperMarket(id);
		if (superMarket != null) {
			superMarketDao.deleteSuperMarket(superMarket);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SuperMarket of id" + id + "data deleted");
			responseStructure.setData(superMarket);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("superMarket id" + id + "not found");
		}
	}

	public ResponseEntity<ResponseStructure<SuperMarket>> validateSuperMarketByEmailAndPassword(String email,
			String password) {
		SuperMarket superMarket = superMarketDao.getUserByEmail(email);
		if (superMarket != null) {
			if (encoder.matches(password, superMarket.getPassword())) {
				ResponseStructure<SuperMarket> responseStructure = new ResponseStructure<SuperMarket>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Valid SuperMarket");
				responseStructure.setData(superMarket);
				return new ResponseEntity<>(responseStructure, HttpStatus.OK);
			} else {
				throw new InvalidCredentialException("Invalid Password");
			}
		} else {
			throw new EmailIdNotFoundException("SuperMarket-Email : " + email + ", NOT Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<SuperMarket>>> getSuperMarketByAddress(String address) {
		ResponseStructure<List<SuperMarket>> responseStructure = new ResponseStructure<>();
		List<SuperMarket> list = superMarketDao.FindByAddress(address);
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Super market fetched by address" + address + "successfully");
			responseStructure.setData(list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("SuperMarket address" + address + "not found");
		}
	}
}