package com.ty.supermarketappspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.ty.supermarketappspringboot.filter.JwtFilter;
import com.ty.supermarketappspringboot.service.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtFilter filter;

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**", "/authenticate", "/admin/save" };

	private static final String[] ADMIN_WHITELIST = { "/admin/update", "/admin/id/{id}", "/admin/getall",
			"/admin/delete/{id}", "/admin/{email}/{password}", "/admin/{phone}/{password}", "/admin/role",
			"/branchadmin/save", "/branchadmin/id/{id}", "/branchadmin/getall", "/branchadmin/delete/{id}",
			"/supermarket/save", "/supermarket/{id}", "/supermarket/delete/{id}", "/supermarket/all",
			"/supermarket/address/{address}" };

	private static final String[] BRANCHADMIN_WHITELIST = { "/branchadmin/update", "/branchadmin/{email}/{password}",
			"/branchadmin/{phone}/{password}", "/stock/save", "/stock/update", "/supermarket/update/{id}",
			"/supermarket/{email}/{password}", "/stock/delete/{id}" };

	private static final String[] STAFF_WHITELIST = { "/staff/update", "/customer/save", "/customer/update",
			"/product/save/{stockId}", "/staff/{email}/{password}", "/staff/{phone}/{password}",
			"/product/uploadProductImage", "/product/downloadProductImage" };

	private static final String[] BRANCHADMIN_STAFF_WHITELIST = { "/product/update/{id}", "/stock", "/stock/id/{id}",
			"/stock/getall", "/stock/getproducts/{type}", "/bills/save/{customerId}" };

	private static final String[] ADMIN_BRANCHADMIN_WHITELIST = { "/staff/save", "/staff/delete", "/staff/{id}",
			"/staff/getall" };

	private static final String[] ADMIN_BRANCHADMIN_STAFF_WHITELIST = { "/bills/id/{id}", "/bills/delete/{id}",
			"/bills/all", "/bills/date", "/product/{id}", "/product/all", "/product/delete/{id}",
			"/product/brand/{brand}", "/product/typeOfProduct/{typeOfProduct}",
			"/product/priceRange/{startPrice}/{endPrice}", "/customer/getbyid/{id}", "/customer/delete/{id}",
			"/customer/getall", "/customer/getbyphone" };

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("Admin realm");
		return entryPoint;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().exceptionHandling().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll().antMatchers(ADMIN_WHITELIST).hasRole("ADMIN")
				.antMatchers(BRANCHADMIN_WHITELIST).hasRole("BRANCH-ADMIN").antMatchers(STAFF_WHITELIST)
				.hasRole("STAFF").antMatchers(ADMIN_BRANCHADMIN_WHITELIST).hasAnyRole("ADMIN", "BRANCH-ADMIN")
				.antMatchers(BRANCHADMIN_STAFF_WHITELIST).hasAnyRole("STAFF", "BRANCH-ADMIN")
				.antMatchers(ADMIN_BRANCHADMIN_STAFF_WHITELIST).hasAnyRole("ADMIN", "BRANCH-ADMIN", "STAFF")
				.anyRequest().authenticated();

		httpSecurity.authenticationProvider(authenticationProvider());

		httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}