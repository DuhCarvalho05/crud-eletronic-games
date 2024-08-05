package infra;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.entities.User.User;

public class UserFileConverter implements FileConvert<User> {

	@Override
	public User generate(String... args) {
		return new User().fromString(args);
	}

	@Override
	public Collection<User> all(Collection<String[]> dataset) {
		Collection<User> users = new ArrayList<>();
        dataset.forEach(user -> users.add(generate(user)));
		return users;
	}

}
