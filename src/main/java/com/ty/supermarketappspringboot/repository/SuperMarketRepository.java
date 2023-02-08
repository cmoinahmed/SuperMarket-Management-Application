package com.ty.supermarketappspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.supermarketappspringboot.dto.SuperMarket;

public interface SuperMarketRepository extends JpaRepository<SuperMarket, Integer> {

	public SuperMarket findById(String id);

	public List<SuperMarket> findByAddress(String address);

	public Optional<SuperMarket> findByEmail(String email);

}
