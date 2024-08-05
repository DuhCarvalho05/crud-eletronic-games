package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Game.Game;
import repository.impl.GameRepository;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/game")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final GameRepository gameRepository;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        this.gameRepository = new GameRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("gameId");
		
		PrintWriter pw = response.getWriter();
		
		if(id == null || id.isEmpty()) {
			Collection<Game> games = gameRepository.findAll();
			games.forEach(game -> pw.write(game.toString() + "\n"));
		}else {
			Game game = gameRepository.find(Long.parseLong(id));
			if(game == null) {
				pw.write("NO CONTENT");
			}else {
				pw.write(game.toString());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		String release = request.getParameter("release");
		String synopsis = request.getParameter("synopsis");
		
		PrintWriter pw = response.getWriter();
		
		if(title.isEmpty() || publisher.isEmpty() || release.isEmpty() || synopsis.isEmpty()) {
			pw.write("FIELDS CAN'T BE EMPTY");
		}else {
			Game game = new Game();
			game.setTitle(title);
			game.setPublisher(publisher);
			game.setRelease(LocalDateTime.parse(release));
			game.setSynopsis(synopsis);
			gameRepository.save(game);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("gameId");
		
		PrintWriter pw = response.getWriter();
		
		if(id == null || id.isEmpty()){
			pw.write("NO CONTENT");
		}else {
			gameRepository.delete(Long.parseLong(id));
		}
	}
	
	

}
