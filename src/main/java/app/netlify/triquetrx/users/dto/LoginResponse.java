package app.netlify.triquetrx.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @AllArgsConstructor @NoArgsConstructor @Data class LoginResponse {
	
	private String token;
	private String name;

}
