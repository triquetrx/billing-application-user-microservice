package app.netlify.triquetrx.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.netlify.triquetrx.users.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);
	Optional<Users> findByEmailId(String emailId);
	
}
