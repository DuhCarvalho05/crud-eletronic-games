package controller.pages.ADMIN.Category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Category.Category;
import repository.impl.CategoryRepository;

/**
 * Servlet implementation class RegisterCategory
 */
@WebServlet("/register-category")
public class RegisterCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryRepository categoryRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCategoryServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		getServletContext().getRequestDispatcher("/ADMIN/register-category.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");

		String url = "/category-list";
		String msg = "error";

		if(!name.isEmpty()) {
			msg = "success";

			Category category = new Category();
			category.setName(name);

			categoryRepository.save(category);
		}

		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
