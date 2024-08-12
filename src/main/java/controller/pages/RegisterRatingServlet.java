package controller.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Game.Game;
import model.entities.Rating.Rating;
import model.entities.User.User;
import repository.impl.GameRepository;
import repository.impl.RatingRepository;
import repository.impl.UserRepository;

/**
 * Servlet implementation class RegisterRating
 */
@WebServlet("/register-rating")
public class RegisterRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final RatingRepository ratingRepository;
	private final UserRepository userRepository;
	private final GameRepository gameRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterRatingServlet() {
        super();
        this.ratingRepository = new RatingRepository();
        this.gameRepository = new GameRepository();
        this.userRepository = new UserRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		String stars = request.getParameter("stars");
		String userId = request.getParameter("userId");
		String gameId = request.getParameter("gameId");
		String url = "/";

		PrintWriter pw = response.getWriter();

		if(description.isEmpty() || stars.isEmpty() || userId.isEmpty() || gameId.isEmpty()) {
			pw.write("FIELDS CANT BE EMPTY");
		}else {
			Rating rating = new Rating();
			rating.setDescription(description);
			rating.setStars(Integer.parseInt(stars));
			rating.setCreatedAt(LocalDateTime.now());

			User user = userRepository.findById(Long.parseLong(userId));
			if(user != null) {
				rating.setUser(user);
			}

			Game game = gameRepository.findById(Long.parseLong(gameId));
			if(game != null) {
				url = "/details/"+gameId;
				rating.setGame(game);
			}

			ratingRepository.save(rating);
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
