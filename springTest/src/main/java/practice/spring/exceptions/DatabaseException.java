package practice.spring.exceptions;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 4418056494873131885L;

	public DatabaseException() {
	}

	public DatabaseException(String paramString) {
		super(paramString);
	}

}
