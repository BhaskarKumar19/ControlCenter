package practice.spring.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

	public User() {
		super();
	}

	public User(User user) {
		super();
		this.userName = user.userName;
		this.userEmail = user.userEmail;
		this.password = user.password;
		this.confirmPassword = user.confirmPassword;
		this.accessToken = user.accessToken;
		this.id = user.id;
	}

	// validation used for this field in validator
	private String userName;
	@Email
	private String userEmail;
	// validation used for this field in validator
	private String password;
	private String confirmPassword;
	private String accessToken;
	private int id;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
