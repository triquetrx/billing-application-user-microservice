package app.netlify.triquetrx.users.exceptions;

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1705201184129739512L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
}
