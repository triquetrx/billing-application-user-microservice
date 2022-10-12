package app.netlify.triquetrx.users.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 7454645660770655278L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
