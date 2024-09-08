package controller.auth;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.entities.User.User;
import model.payload.Auth.LoginRequest;
import repository.impl.CategoryRepository;
import repository.impl.UserRepository;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserRepository userRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		LoginRequest loginRequest = gson.fromJson(reader, LoginRequest.class);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(loginRequest.email() != null && !loginRequest.email().isEmpty() 
				&& loginRequest.password() != null && !loginRequest.password().isEmpty()) {
			User user = userRepository.findByEmail(loginRequest.email());

			if(user != null && User.autenticate(user, loginRequest.password())) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.setStatus(200);
			}else {				
				response.setStatus(404);
			}
		}else {
			response.setStatus(400);
		}
		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.setStatus(200);
	}

}
