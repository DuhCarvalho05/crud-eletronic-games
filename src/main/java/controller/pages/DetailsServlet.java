package controller.pages;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Game.Game;
import model.entities.Rating.Rating;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;
import repository.impl.RatingRepository;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/details/*")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;
	private final RatingRepository ratingRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
        this.gameRepository = new GameRepository();
        this.ratingRepository =  new RatingRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		
		String gameId = "";
		
		if(request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
			gameId = request.getPathInfo().substring(1);
		}
		
		String url = "/home";
		String msg = "not-found";
		
		try {
			Long id = Long.parseLong(gameId);
			Game game = gameRepository.findById(id);
			
			if(game != null) {
				Collection<Rating> ratings = ratingRepository.findByGameId(id);
				
				getServletContext().setAttribute("game", game);
				getServletContext().setAttribute("ratings", ratings);
				url = "/details.jsp";
			}
			
		}catch(NumberFormatException e) {
			msg = "nan";
		}
		
		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	

}
