package com.ty.supermarketappspringboot.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class SuperMarket {
	@Id
	@GenericGenerator(name = "sequence_supermarket_id", strategy = "com.ty.supermarketappspringboot.dto.SuperMarketIdGenerator")
	@GeneratedValue(generator = "sequence_supermarket_id")
	@ApiModelProperty(value = "AutoGenerated")
	private String id;

	@NotBlank(message = "Please Enter The SuperMarket-Name")
	@ApiModelProperty(required = true)
	private String name;

	@NotBlank(message = "Please Enter The SuperMarket-Address")
	@ApiModelProperty(required = true)
	private String address;

	@Column(unique = true)
	@NotBlank(message = "Please Enter the Valid SuperMarket-Email")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Enter a Valid Email")
	@ApiModelProperty(required = true)
	private String email;

	@Column(unique = true)
	@NotBlank(message = "Please Enter the SuperMarket-PhoneNumber")
	@ApiModelProperty(required = true)
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}+$", message = "Enter Proper SuperMarket-PhoneNumber")
	private String phone;


	@NotBlank(message = "Please Enter the User-Password")
	@ApiModelProperty(required = true)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})", message = "Enter Proper User-Password "
			+ "\n" + " The User-Password Should Have Atleast " + "\n" + " 1 Upper Case " + "\n" + " 1 Lower Case "
			+ "\n" + " 1 Special Character " + "\n" + " And 1 Numric Character " + "\n"
			+ " The Length OF The Password Must Be Minimum OF 6 Character And Maximum OF 15 Character ")

	private String password;

	@OneToMany(mappedBy = "superMarket", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Stock> stocks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

}
