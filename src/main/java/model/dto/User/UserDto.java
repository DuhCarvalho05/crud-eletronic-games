package model.dto.User;

import fileSystem.Writable;
import model.entities.User.UserType;

public class UserDto implements Writable<UserDto> {

	private Long id;
	private String name;
	private String email;
	private String password;
	private UserType type;

	public UserDto(){}

	public UserDto(Long id, String name, String email, String password, UserType type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

	@Override
	public UserDto fromString(String... args) {
		return new UserDto(Long.parseLong(args[0]), args[1], args[2], args[3], UserType.valueOf(args[4]));
	}

	@Override
	public String toString() {
		return String.format("%d;%s;%s;%s;%s", this.id, this.name, this.email, this.password, this.type);
	}


}
