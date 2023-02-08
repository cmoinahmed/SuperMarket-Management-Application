package com.ty.supermarketappspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.supermarketappspringboot.dto.SuperMarket;
import com.ty.supermarketappspringboot.repository.SuperMarketRepository;

@Repository
public class SuperMarketDao {

	@Autowired
	SuperMarketRepository superMarketRepository;

	public SuperMarket createSuperMarket(SuperMarket superMarket) {
		return superMarketRepository.save(superMarket);
	}

	public SuperMarket getSuperMarket(String id) {
		return superMarketRepository.findById(id);
	}

	public List<SuperMarket> getAllSuperMarket() {
		return superMarketRepository.findAll();
	}

	public SuperMarket updateSuperMarket(String id) {
		return superMarketRepository.findById(id);

	}

	public void deleteSuperMarket(SuperMarket superMarket) {
		superMarketRepository.delete(superMarket);
	}

	public List<SuperMarket> FindByAddress(String address) {
		return superMarketRepository.findByAddress(address);
	}

	public SuperMarket getUserByEmail(String email) {
		Optional<SuperMarket> optional = superMarketRepository.findByEmail(email);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
