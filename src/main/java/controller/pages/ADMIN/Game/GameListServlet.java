package controller.pages.ADMIN.Game;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.impl.CategoryRepository;
import repository.impl.GameRepository;

/**
 * Servlet implementation class GameListServlet
 */
@WebServlet("/game-list")
public class GameListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameListServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
        this.gameRepository = new GameRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		getServletContext().setAttribute("games", gameRepository.findAll());
		getServletContext().getRequestDispatcher("/ADMIN/game-list.jsp").forward(request, response);
	}

}
