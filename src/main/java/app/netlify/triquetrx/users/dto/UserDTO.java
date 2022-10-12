package app.netlify.triquetrx.users.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @AllArgsConstructor @NoArgsConstructor class UserDTO {
	
	@NotEmpty(message = "name must not be empty")
	@Size(min = 2,message = "Name should have minimum 2 characters")
	private String name;
	@NotEmpty(message = "emailId must not be empty")
	@Email
	private String emailId;
	@NotEmpty(message = "username must not be empty")
	private String username;
	@NotEmpty(message = "organizationName must not be empty")
	private String organizationName;
	@Range(min = 10, max = 10,message = "phoneNumber must have 10 digits")
	private long phoneNumber;
	@NotEmpty(message = "address1 must not be empty")
	private String address1;
	private String address2;
	@NotEmpty(message = "city must not be empty")
	private String city;
	@Pattern(regexp = "^([0-9]{2}[0-9a-zA-Z]{10}[0-9]{1}[Z]{1}[0-9A-Z]{1})$",message = "Please enter a valid GST Number")
	@NotEmpty(message = "gstNumber must not be empty")
	private String gstNumber;
	@NotEmpty(message = "password must not be empty")
	private String password;

}
