package controller.pages.ADMIN.Game;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Game.Game;
import repository.impl.GameRepository;

/**
 * Servlet implementation class DeleteGameServlet
 */
@WebServlet("/delete-game/*")
public class DeleteGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final GameRepository gameRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGameServlet() {
        super();
        this.gameRepository = new GameRepository();
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
		String gameId = "";

		if (request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
			gameId = request.getPathInfo().substring(1);
		}

		String url = "/ADMIN/game-list.jsp";
		String msg = "error";
		
		try {
			Long id = Long.parseLong(gameId);
			Game game = gameRepository.findById(id);

			if (game != null) {
				gameRepository.deleteById(id);
				msg = "deleted";
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			msg = "nan";
		}
		
		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
