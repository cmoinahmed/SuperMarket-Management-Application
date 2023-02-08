package com.ty.supermarketappspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.supermarketappspringboot.dao.CustomerDao;
import com.ty.supermarketappspringboot.dto.Customer;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.exception.DataNotFoundException;
import com.ty.supermarketappspringboot.exception.IdNotFoundException;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Customer>> registerCustomer(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Successfully Customer Saved");
		responseStructure.setData(customerDao.saveCustomer(customer));
		return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(String id) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer = customerDao.getCustomer(id);
		if (customer != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Customer Fetched By Id Successfully");
			responseStructure.setData(customer);
			return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(String id) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer = customerDao.getCustomer(id);
		if (customer != null) {
			customerDao.deleteCustomer(customer);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully Customer Deleted");
			responseStructure.setData(customer);
			return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
		List<Customer> customers = customerDao.getAllCustomer();
		if (customers != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully Fetched All Customer");
			responseStructure.setData(customers);
			return new ResponseEntity<ResponseStructure<List<Customer>>>(responseStructure, HttpStatus.OK);

		}
		throw new DataNotFoundException("Customer Data Not Present");
	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(String id, Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer2 = customerDao.getCustomer(id);
		if (customer2 != null) {
			customer2.setName(customer.getName());
			customer2.setPhone(customer.getPhone());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully Customer Updated");
			responseStructure.setData(customerDao.saveCustomer(customer2));
			return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(String phone) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer = customerDao.getCustomerByPhone(phone);
		if (customer != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully Fetched Customer By PhoneNumber");
			responseStructure.setData(customer);
			return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "PhoneNumber " + phone + " Not Found";
			throw new IdNotFoundException(msg);
		}
	}

}
