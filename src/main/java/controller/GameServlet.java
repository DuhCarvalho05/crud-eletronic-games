package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entities.Category.Category;
import model.entities.Game.Game;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/game/*")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
        this.gameRepository = new GameRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		
		if(title != null && !title.isEmpty()) {
			List<Game> gamesOfThisSearch = (List<Game>) gameRepository.searchByTitle(title);
			String json = new Gson().toJson(gamesOfThisSearch);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}else {
			String gameIdStr = "";

			if(request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
				gameIdStr = request.getPathInfo().substring(1);
			}
			
			if(!gameIdStr.isEmpty()) {
				try {
					Long gameId = Long.parseLong(gameIdStr);
					
					Game game = gameRepository.findById(gameId);
					
					if(game != null) {
						String json = new Gson().toJson(game);
						
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(json);
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				List<Game> games = (List<Game>) gameRepository.findAll();
				String json = new Gson().toJson(games);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
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
