package controller.pages;

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
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/catalog/*")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
        this.gameRepository = new GameRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		
		String categoryName = "";
		
		if(request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
			categoryName = request.getPathInfo().substring(1);
		}
		
		String url = "/home";
		String msg = "not-found";
		
		Category category = categoryRepository.findByName(categoryName);
		getServletContext().setAttribute("category", category);
		
		if(category != null) {
			Collection<Game> gamesOfThisCategory = gameRepository.findAllByCategory(category.getId());
			getServletContext().setAttribute("games", gamesOfThisCategory);
			url = "/catalog.jsp";
		}
		
		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
