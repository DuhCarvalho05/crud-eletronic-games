package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.impl.UserRepository;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/logado.jsp";
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserRepository userRepository = new UserRepository();
		
		// GAMBIARRA
		var user = userRepository.findAll().stream()
			.filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst();
		
		System.out.println(user);
		
		if(user.isEmpty()) {
			url = "/deuErrado.jsp";
		}
		
		// CERTO
//		var parameters = new HashMap<String, Object>();
//		parameters.put("email", email);
//		parameters.put("password", password);
//		userRepository.findAll(parameters);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
