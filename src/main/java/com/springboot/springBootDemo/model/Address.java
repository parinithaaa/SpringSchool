package com.springboot.springBootDemo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address extends BaseEntity{
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int addressId;

	    @NotBlank(message="Address1 must not be blank")
	    @Size(min=5, message="Address1 must be at least 5 characters long")
	    private String address1;

	    private String address2;

	    @NotBlank(message="City must not be blank")
	    @Size(min=5, message="City must be at least 5 characters long")
	    private String city;

	    @NotBlank(message="State must not be blank")
	    @Size(min=5, message="State must be at least 5 characters long")
	    private String state;

	    @NotBlank(message="Zip Code must not be blank")
	    @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
	    private String zipCode;

}
