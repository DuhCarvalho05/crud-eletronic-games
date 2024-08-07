package infra.User;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.dto.User.UserDto;

public class UserFileConverter implements FileConvert<UserDto> {

	@Override
	public UserDto generate(String... args) {
		return new UserDto().fromString(args);
	}

	@Override
	public Collection<UserDto> all(Collection<String[]> dataset) {
		Collection<UserDto> usersDto = new ArrayList<>();
        dataset.forEach(user -> usersDto.add(generate(user)));
		return usersDto;
	}

}
