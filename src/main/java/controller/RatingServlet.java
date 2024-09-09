package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entities.Game.Game;
import model.entities.Rating.Rating;
import model.entities.User.User;
import model.payload.Category.CategoryRequest;
import model.payload.Rating.SendRatingRequest;
import repository.impl.GameRepository;
import repository.impl.RatingRepository;
import repository.impl.UserRepository;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet("/ratings")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final RatingRepository ratingRepository;
	private final UserRepository userRepository;
	private final GameRepository gameRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
        super();
        this.ratingRepository = new RatingRepository();
        this.userRepository = new UserRepository();
        this.gameRepository = new GameRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ratingId = request.getParameter("ratingId");
		String gameId = request.getParameter("gameId");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(ratingId != null && !ratingId.isEmpty()) {
			
			try {
				Long id = Long.parseLong(ratingId);
				
				Rating rating = ratingRepository.findById(id);
				String json = new Gson().toJson(rating);
				
				response.getWriter().write(json);
				response.setStatus(200);
			}catch(NumberFormatException e) {
				response.setStatus(404);
			}
			
		}else {
			
			if(gameId != null && !gameId.isEmpty()) {
				
				try {
					
					Long id = Long.parseLong(gameId);
					List<Rating> ratingsFromGame = (List<Rating>) ratingRepository.findByGameId(id);
					
					String json = new Gson().toJson(ratingsFromGame);
					
					response.getWriter().write(json);
					response.setStatus(200);
					
				}catch(NumberFormatException e) {
					response.setStatus(404);
				}
				
			}else {
				List<Rating> ratings = (List<Rating>) ratingRepository.findAll();
				String json = new Gson().toJson(ratings);
			
				response.getWriter().write(json);
				response.setStatus(200);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();

		SendRatingRequest sendRatingRequest = gson.fromJson(reader, SendRatingRequest.class);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if(sendRatingRequest != null 
				&& !sendRatingRequest.description().isEmpty()
				&& sendRatingRequest.userId() != null
				&& sendRatingRequest.gameId() != null
				&& sendRatingRequest.stars() != 0) {
			
			Rating rating = new Rating();
			rating.setDescription(sendRatingRequest.description());
			rating.setStars(sendRatingRequest.stars());
			rating.setCreatedAt(LocalDateTime.now().toString());

			User user = userRepository.findById(sendRatingRequest.userId());
			if(user != null) {
				rating.setUser(user);
			}

			Game game = gameRepository.findById(sendRatingRequest.gameId());
			if(game != null) {
				rating.setGame(game);
			}

			ratingRepository.save(rating);
			response.setStatus(200);
		}else {
			response.setStatus(400);
		}
		
		
	}

}
