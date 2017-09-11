package model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 8294180014912103005L;

	private String username;
	private transient String password;

	private static String testStatic;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTestStatic() {
		return testStatic;
	}

	public void setTestStatic(String testStatic) {
		User.testStatic = testStatic;
	}


	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
