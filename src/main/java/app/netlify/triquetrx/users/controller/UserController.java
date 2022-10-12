package app.netlify.triquetrx.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import app.netlify.triquetrx.users.dto.LoginDTO;
import app.netlify.triquetrx.users.dto.LoginResponse;
import app.netlify.triquetrx.users.dto.UserDTO;
import app.netlify.triquetrx.users.exceptions.InvalidDataAccessException;
import app.netlify.triquetrx.users.exceptions.UserAlreadyExistsException;
import app.netlify.triquetrx.users.exceptions.UserCreationException;
import app.netlify.triquetrx.users.exceptions.UserNotFoundException;
import app.netlify.triquetrx.users.model.Message;
import app.netlify.triquetrx.users.model.Users;
import app.netlify.triquetrx.users.service.UserService;
import feign.FeignException.FeignClientException;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO dto) {
		try {
			LoginResponse loginUser = service.loginUser(dto);
			return new ResponseEntity<>(loginUser, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(message[5], HttpStatus.valueOf(errCode));
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDTO dto){
		try {
			String response = service.createNewUser(dto);
			return new ResponseEntity<>(new Message(response),HttpStatus.OK);
		} catch (UserAlreadyExistsException | UserCreationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(message[5], HttpStatus.valueOf(errCode));
		} 
	}
	
	@GetMapping("/about-me")
	public ResponseEntity<?> getMyDetails(@RequestHeader(name="Authorization") String token){
		try {
			Users response = service.getMyData(token);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (InvalidDataAccessException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(message[5], HttpStatus.valueOf(errCode));
		}
	}
	
	

}
