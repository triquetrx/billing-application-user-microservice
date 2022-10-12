package app.netlify.triquetrx.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @AllArgsConstructor @NoArgsConstructor @Data class SignupDTO {
	
	private String username;
	private String password;
	private long uid;
	

}
