package com.springboot.springBootDemo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="contact_msg")

public class Contact extends BaseEntity{
	@NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
	@Id
	String name;
	
	@NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	String mobileNum;
	
	
	@NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
	String email;
	
	 @NotBlank(message="Subject must not be blank")
	 @Size(min=5, message="Subject must be at least 5 characters long")
	String subject;
	 

	    @NotBlank(message="Message must not be blank")
	    @Size(min=10, message="Message must be at least 10 characters long")
	String message;

	    private int contactId;
	    private String status;
}
