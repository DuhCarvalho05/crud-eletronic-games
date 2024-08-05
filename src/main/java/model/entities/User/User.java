package model.entities.User;

import fileSystem.ObjectConvert;

public class User implements ObjectConvert<User> {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private UserType type;
	
	public User() {
	}
	
	private User(Long id, String name, String email, String password, UserType type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public User(String name, String email, String password, UserType type) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public User fromString(String... args) {
		return new User(Long.parseLong(args[0]), args[1], args[2], args[3], UserType.valueOf(args[4]));
	}
	
	@Override
	public String toString() {
		return String.format("%d;%s;%s;%s;%s", this.id, this.name, this.email, this.password, this.type);
	}
	
}
