package repository.impl;

import java.util.Collection;
import java.util.Map;

import fileSystem.FileInterpreter;
import fileSystem.FileManagement;
import infra.UserFileConverter;
import model.entities.User.User;
import repository.IRepository;

public class UserRepository implements IRepository<User, Long> {

	private static Long SEQUENCE = 0L;
	
	private final FileManagement fileManagement;
    private final FileInterpreter fileInterpreter;
    private final UserFileConverter userFileConverter;

    public UserRepository() {
        this.fileManagement = new FileManagement("/Users/caiolopes/Downloads/user.csv");
        this.fileInterpreter = new FileInterpreter();
        this.userFileConverter = new UserFileConverter();
    }
	
	@Override
	public void save(User user) {
		if(user.getId() == null) {
			user.setId(++SEQUENCE);
		}
		
		delete(user.getId());
        fileManagement.write(user);
		
	}

	@Override
	public void saveAll(Collection<User> collection) {
		for (User user : collection) {
            save(user);
        }
		
	}

	@Override
	public User find(Long identifier) {
		Collection<User> users = findAll();
        for (User user : users) {
            if(user.getId().equals(identifier)){
                return user;
            }
        }
        return null;
	}

	@Override
	public Collection<User> findAll() {
		return userFileConverter.all(fileInterpreter.interpret(fileManagement.read(), User.class));
	}

	@Override
	public Collection<User> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long identifier) {
		Collection<User> users = findAll();
        fileManagement.clear();
        users.removeIf(user -> user.getId().equals(identifier));
        saveAll(users);
	}

	@Override
	public void deleteAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

}
