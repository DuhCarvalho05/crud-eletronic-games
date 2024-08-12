package controller.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.User.User;
import model.entities.User.UserType;
import repository.impl.CategoryRepository;
import repository.impl.UserRepository;

/**
 * Servlet implementation class SingupServlet
 */
@WebServlet("/singup")
public class SingupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingupServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
        this.userRepository = new UserRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		getServletContext().removeAttribute("msg");
		getServletContext().getRequestDispatcher("/singup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String url = "/singup.jsp";
		String msg = "Preencha todos os campos";

		if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

			User existentUser = userRepository.findByEmail(email);

			if(existentUser != null) {
				msg = "Email já cadastrado";
			}else {
				User user = new User(name, email, password, UserType.DEFAULT);
				userRepository.save(user);
				msg = "success";
				url = "/login.jsp";
			}
		}

		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
