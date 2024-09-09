package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import model.entities.Category.Category;
import model.entities.Game.Game;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;
import utils.PathFile;


@WebServlet("/game/*")

@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)


public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String uploadPath = PathFile.getInstance().getPath() + "/images/";
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		String release = request.getParameter("release");
		String synopsis = request.getParameter("synopsis");
		String categoryId = request.getParameter("categoryId");
		String requirementNotMapped = request.getParameter("requirement");
		String platformNotMapped = request.getParameter("platform");
		Part imageNotMapped = request.getPart("image");



		if(title.isEmpty() || publisher.isEmpty() || release.isEmpty() || synopsis.isEmpty() || categoryId.isEmpty() || requirementNotMapped.isEmpty() || platformNotMapped.isEmpty()) {
			response.setStatus(206);
		}else {
			Game game = new Game();
			game.setTitle(title);
			game.setPublisher(publisher);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			game.setRelease(LocalDateTime.parse(release + " 00:00", formatter).toString());
			game.setSynopsis(synopsis);

			Map<String, String> requirement = new HashMap<>();
			String[] lines = requirementNotMapped.split("\n");
			for(String line : lines) {
				String[] tuples = line.split(";");
				requirement.put(tuples[0], tuples[1]);
			}

			game.setRequirement(requirement);

			Collection<String> platform = new ArrayList<>();
			String[] values = platformNotMapped.split(";");
			for(String value : values) {
				platform.add(value);
			}

			game.setPlatform(platform);

			Category category = categoryRepository.findById(Long.parseLong(categoryId));

			if(category != null) {
				game.setCategory(category);
			}

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}


			if(!imageNotMapped.getName().isEmpty()) {
				String fileName = generateFileName(getFileName(imageNotMapped));
				imageNotMapped.write(uploadPath + File.separator + fileName);
				game.setImageName(fileName);
			}

			gameRepository.save(game);
			response.setStatus(200);
		}
		
	}
		

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gameId = req.getParameter("gameId");
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		if(gameId != null && !gameId.isEmpty()) {
			try {
				Long id = Long.parseLong(gameId);
				Game game = gameRepository.findById(id);

				if (game != null) {
					gameRepository.deleteById(id);
					resp.setStatus(200);
					
				}else {
					resp.setStatus(406);
				}

			} catch (NumberFormatException e) {
				resp.setStatus(404);
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		String gameId = req.getParameter("id");
		String title = req.getParameter("title");
		String publisher = req.getParameter("publisher");
		LocalDateTime release = LocalDateTime.parse(req.getParameter("release"));
		String synopsis = req.getParameter("synopsis");
		Long categoryId = Long.parseLong(req.getParameter("categoryId"));
		String requirementNotMapped = req.getParameter("requirement");
		String platformNotMapped = req.getParameter("platform");
		Part imageNotMapped = req.getPart("image");


		try {
			Long id = Long.parseLong(gameId);
			Game game = gameRepository.findById(id);

			if (game != null) {

				if(!title.isBlank() || title != game.getTitle()) {game.setTitle(title);}
				if(!publisher.isBlank() || publisher != game.getPublisher()) {game.setPublisher(publisher);}
				if(release != null || !release.equals(game.getRelease())) {game.setRelease(release);}
				if(!synopsis.isBlank()|| synopsis != game.getSynopsis()) {game.setSynopsis(synopsis);}
				if(categoryId != null || categoryId != game.getCategory().getId()) {game.setCategory(categoryRepository.findById(categoryId));}


				if(requirementNotMapped != null ) {
					if(!requirementNotMapped.isBlank()) {
						Map<String, String> requirement = new HashMap<>();
						String[] lines = requirementNotMapped.split("\n");
						for(String line : lines) {
							String[] tuples = line.split(";");
							requirement.put(tuples[0], tuples[1]);
						}
						if(!requirement.equals(game.getRequirement())) {
							game.setRequirement(requirement);
						}
					}


				}

				if(platformNotMapped != null) {
					if(!platformNotMapped.isBlank()) {
						Collection<String> platform = new ArrayList<>();
						String[] values = platformNotMapped.split(";");
						for(String value : values) {
							platform.add(value);
						}
						if(!platform.equals(game.getPlatform())) {
						  game.setPlatform(platform);
						}
					}

				}
				File uploadDir = new File(uploadPath);


				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				String fileName = generateFileName(getFileName(imageNotMapped));
				if(!fileName.isBlank()) {
					imageNotMapped.write(uploadPath + File.separator + fileName);
					game.setImageName(fileName);
				}
				gameRepository.save(game);
			}
	}

	private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

	private String generateFileName(String actualFileName) {
		if(actualFileName.isEmpty()) {
			return "";
		}

		String[] values = actualFileName.split("\\.");
		String ext = values[1];

		Long instant = System.currentTimeMillis();

		return String.format("%s.%s",instant, ext);
	}

}
