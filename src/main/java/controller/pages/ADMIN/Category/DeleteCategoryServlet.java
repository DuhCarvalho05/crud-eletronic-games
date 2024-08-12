	package controller.pages.ADMIN.Category;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Category.Category;
import model.entities.Game.Game;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;

/**
 * Servlet implementation class DeleteCategoryServlet
 */
@WebServlet("/delete-category/*")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCategoryServlet() {
		super();
		this.categoryRepository = new CategoryRepository();
		this.gameRepository = new GameRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = "";

		if (request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
			categoryId = request.getPathInfo().substring(1);
		}

		String url = "/update-category";
		String msg = "error";

		try {
			Long id = Long.parseLong(categoryId);
			Category category = categoryRepository.findById(id);

			if (category != null) {
				
				Collection<Game> gamesWithThisCategory = gameRepository.findAllByCategory(id);
				
				if(gamesWithThisCategory.isEmpty()) {					
					categoryRepository.deleteById(id);
					msg = "deleted";
				}else {
					msg = "error";
				}
				
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			msg = "nan";
		}

		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
