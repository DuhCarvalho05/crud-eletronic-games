package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entities.Rating.Rating;
import repository.impl.RatingRepository;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet("/ratings")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final RatingRepository ratingRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
        super();
        this.ratingRepository = new RatingRepository();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
