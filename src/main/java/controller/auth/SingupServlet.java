package controller.auth;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entities.User.User;
import model.entities.User.UserType;
import model.payload.Auth.LoginRequest;
import model.payload.Auth.SingUpRequest;
import repository.impl.CategoryRepository;
import repository.impl.UserRepository;

/**
 * Servlet implementation class SingupServlet
 */
@WebServlet("/auth/singup")
public class SingupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserRepository userRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingupServlet() {
        super();
        this.userRepository = new UserRepository();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		SingUpRequest signUpRequest = gson.fromJson(reader, SingUpRequest.class);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if(signUpRequest.name() != null && !signUpRequest.name().isEmpty() 
				&& signUpRequest.email() != null && !signUpRequest.email().isEmpty() 
				&& signUpRequest.password() != null && !signUpRequest.password().isEmpty()) {

			User existentUser = userRepository.findByEmail(signUpRequest.email());

			if(existentUser != null) {
				response.setStatus(409);
			}else {
				User user = new User(signUpRequest.name(), signUpRequest.email(), signUpRequest.password(), UserType.DEFAULT);
				userRepository.save(user);
				response.setStatus(201);
			}
		}else {
			response.setStatus(400);
		}
	}

}
