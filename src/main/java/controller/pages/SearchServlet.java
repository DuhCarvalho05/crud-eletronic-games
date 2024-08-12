package controller.pages;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Game.Game;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		this.gameRepository = new GameRepository();
		this.categoryRepository = new CategoryRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());

		String query = request.getParameter("q");

		String url = "/";
		String msg = "not-found";

		getServletContext().setAttribute("query", query);

		if(query != null && !query.isEmpty()) {
			Collection<Game> gamesOfThisSearch = gameRepository.searchByTitle(query);
			getServletContext().setAttribute("games", gamesOfThisSearch);
			url = "/search.jsp";
		}

		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
