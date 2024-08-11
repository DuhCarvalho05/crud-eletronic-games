package controller.pages.ADMIN.Category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.impl.CategoryRepository;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/category-list")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final CategoryRepository categoryRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryListServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		getServletContext().getRequestDispatcher("/ADMIN/category-list.jsp").forward(request, response);
	}

}
