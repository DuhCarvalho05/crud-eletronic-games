package model.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.User.User;
import model.entities.User.UserType;
import repository.impl.UserRepository;

public class UserService {

	private final UserRepository userRepository;

	public UserService() {
		this.userRepository = new UserRepository();
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		PrintWriter pw = response.getWriter();

		if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
			pw.write("FIELDS CAN'T BE EMPTY");
		}else{
			User user = new User(name, email, password, UserType.DEFAULT);
			userRepository.save(user);
		}
	}

	public void retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userId");

		PrintWriter pw = response.getWriter();

		if(id == null || id.isEmpty()) {
			Collection<User> users = userRepository.findAll();
			users.forEach(user -> pw.write(user.toString() + "\n"));
		}else {
			User user = userRepository.findById(Long.parseLong(id));
			if(user == null) {
				pw.write("NO CONTENT");
			}else {
				pw.write(user.toString());
			}
		}
	}

	public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userId");

		PrintWriter pw = response.getWriter();

		if(id == null || id.isEmpty()){
			pw.write("NO CONTENT");
		}else {
			userRepository.deleteById(Long.parseLong(id));
		}

	}

}
