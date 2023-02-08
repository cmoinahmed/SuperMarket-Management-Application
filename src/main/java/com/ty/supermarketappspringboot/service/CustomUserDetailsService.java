package com.ty.supermarketappspringboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.supermarketappspringboot.dto.User;
import com.ty.supermarketappspringboot.exception.InvalidCredentialException;
import com.ty.supermarketappspringboot.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByEmail(email);
		CustomUserDetails customUserDetails = new CustomUserDetails();

		if (optional.isPresent()) {
			User user = optional.get();
			System.out.println(user.getRole());
			customUserDetails.setUser(user);
		} else {
			throw new InvalidCredentialException("Invalid User email:"+ email +" or password");
		}

		return customUserDetails;
	}

}
