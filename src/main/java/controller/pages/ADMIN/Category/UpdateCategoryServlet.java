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
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/update-category/*")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final CategoryRepository categoryRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		
		String categoryId = "";
		
		if(request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
			categoryId = request.getPathInfo().substring(1);
		}
		
		String url = "/ADMIN/category-list.jsp";
		String msg = "not-found";
		
		try {
			Long id = Long.parseLong(categoryId);
			Category category = categoryRepository.findById(id);
			
			getServletContext().setAttribute("category", category);
			url = "/ADMIN/update-category.jsp";
		}catch(NumberFormatException e) {
			e.printStackTrace();
			msg = "nan";
		}
		
		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("id");
		String name = request.getParameter("name");

		String url = "/update-category";
		String msg = "empty-fields";	
		
		if(!name.isEmpty()) {
			try {
				Long id = Long.parseLong(categoryId);
				Category category = categoryRepository.findById(id);
				
				if(category != null) {
					category.setName(name);
					categoryRepository.save(category);
				}
				
				url = "/category-list";
			}catch(NumberFormatException e) {
				e.printStackTrace();
				msg = "nan";
			}
		}
		
		
		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
