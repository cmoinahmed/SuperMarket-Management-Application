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

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GenericGenerator(name = "sequence_customer_id", strategy = "com.ty.supermarketappspringboot.dto.CustomerIdGenerator")
	@GeneratedValue(generator = "sequence_customer_id")
	@ApiModelProperty(value = "AutoGenerated")
	private String id;

	@NotBlank(message = "Please Enter The Customer-Name")
	@ApiModelProperty(required = true)
	private String name;

	@Column(unique = true)
	@NotBlank(message = "Please Enter the Customer-PhoneNumber")
	@ApiModelProperty(required = true)
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}+$", message = "Enter Proper Customer-PhoneNumber")
	private String phone;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Bill> bills;

}
