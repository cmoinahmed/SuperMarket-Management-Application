package com.ty.supermarketappspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.supermarketappspringboot.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByPhone(String phone);
	
	public Customer findById(String id);
}
