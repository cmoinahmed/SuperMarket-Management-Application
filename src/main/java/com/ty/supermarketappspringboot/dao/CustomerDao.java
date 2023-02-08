package com.ty.supermarketappspringboot.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.supermarketappspringboot.dto.Customer;
import com.ty.supermarketappspringboot.repository.CustomerRepository;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerRepository repository;
	
	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}
	public Customer getCustomer(String id) {
		return repository.findById(id);
	}
	public List<Customer> getAllCustomer() {
		return repository.findAll();
	}
	
	public Customer deleteCustomer(Customer customer) {
		repository.delete(customer);
		return customer;
	}
	public Customer getCustomerByPhone(String phone) {
		return repository.findByPhone(phone);
	}

}
