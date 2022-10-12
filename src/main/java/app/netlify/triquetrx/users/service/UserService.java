package app.netlify.triquetrx.users.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.netlify.triquetrx.users.client.AuthenticationClient;
import app.netlify.triquetrx.users.dto.LoginDTO;
import app.netlify.triquetrx.users.dto.LoginResponse;
import app.netlify.triquetrx.users.dto.SignupDTO;
import app.netlify.triquetrx.users.dto.UserDTO;
import app.netlify.triquetrx.users.dto.ValidationResponse;
import app.netlify.triquetrx.users.exceptions.InvalidDataAccessException;
import app.netlify.triquetrx.users.exceptions.UserAlreadyExistsException;
import app.netlify.triquetrx.users.exceptions.UserCreationException;
import app.netlify.triquetrx.users.exceptions.UserNotFoundException;
import app.netlify.triquetrx.users.model.Users;
import app.netlify.triquetrx.users.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private AuthenticationClient authenticationClient;

	@Transactional
	public LoginResponse loginUser(LoginDTO dto) throws UserNotFoundException {
		Optional<Users> user = repository.findByUsername(dto.getUsername());
		if (user.isPresent()) {
			String token = authenticationClient.loginUser(dto);
			return new LoginResponse(token, user.get().getName());
		}
		throw new UserNotFoundException("Username does not exists");
	}
	
	@Transactional
	public String createNewUser(UserDTO dto) throws UserAlreadyExistsException, UserCreationException {
		if(!isUserPresent(dto.getUsername(), dto.getEmailId())) {
			Users user = repository.save(new Users(dto.getName(), dto.getOrganizationName(),dto.getEmailId(), dto.getUsername(), dto.getPhoneNumber(), dto.getAddress1(), dto.getAddress2(), dto.getCity(), dto.getGstNumber()));
			String signupUser = authenticationClient.signupUser(new SignupDTO(dto.getUsername(), dto.getPassword(), user.getUid()));
			if(signupUser.equalsIgnoreCase("User Created")) {
				return "New User Created";
			}
			repository.delete(user);
			throw new UserCreationException("User can not be created");
		}
		throw new UserAlreadyExistsException("Username or email already exists");
	}
	
	@Transactional
	public boolean isUserPresent(String username,String email) {
		Optional<Users> findByEmail = repository.findByEmailId(email);
		Optional<Users> findByUsername = repository.findByUsername(username);
		if(findByEmail.isPresent() || findByUsername.isPresent()) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public Users getMyData(String token) throws InvalidDataAccessException {
		ValidationResponse validator = authenticationClient.validateAuthentication(token);
		if(validator.isStatus()) {
			return repository.findByUsername(validator.getUsername()).get();
		}
		throw new InvalidDataAccessException("Invalid Login");
	}

}
