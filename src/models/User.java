package models;

public class User {
	
	private String SSN; 
	private String name;
	private String username;
	private String password; 
	private String role;
	
	public User(String SSN, String name, String username, String password, String role) {
		this.SSN=SSN;
		this.name=name;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [SSN=" + SSN + ", name=" + name + ", username=" + username
				+ ", password=" + password + ", role=" + role + "]";
	}
	
}
