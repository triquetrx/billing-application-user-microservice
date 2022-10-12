package app.netlify.triquetrx.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import app.netlify.triquetrx.users.dto.LoginDTO;
import app.netlify.triquetrx.users.dto.SignupDTO;
import app.netlify.triquetrx.users.dto.ValidationResponse;

@FeignClient(name = "authentication-microservice",url = "http://localhost:8000")
public interface AuthenticationClient {
	
	@GetMapping("/validate")
	public ValidationResponse validateAuthentication(@RequestHeader(name = "Authorization") String token);
	
	@PostMapping("/login")
	public String loginUser(@RequestBody LoginDTO loginDTO);
	
	@PostMapping("/signup")
	public String signupUser(@RequestBody SignupDTO signupDTO);

}
