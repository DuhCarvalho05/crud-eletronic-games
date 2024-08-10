package model.services;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.entities.Category.Category;
import model.entities.Game.Game;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;

public class GameService {

	private final String uploadPath = "/Users/caiolopes/images/";

	private final GameRepository gameRepository;
	private final CategoryRepository categoryRepository;

	public GameService() {
		this.gameRepository = new GameRepository();
		this.categoryRepository = new CategoryRepository();
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		String release = request.getParameter("release");
		String synopsis = request.getParameter("synopsis");
		String categoryId = request.getParameter("categoryId");
		String requirementNotMapped = request.getParameter("requirement");
		String platformNotMapped = request.getParameter("platform");
		Part imageNotMapped = request.getPart("image");

		PrintWriter pw = response.getWriter();

		if(title.isEmpty() || publisher.isEmpty() || release.isEmpty() || synopsis.isEmpty() || categoryId.isEmpty() || requirementNotMapped.isEmpty() || platformNotMapped.isEmpty()) {
			pw.write("FIELDS CAN'T BE EMPTY");
		}else {
			Game game = new Game();
			game.setTitle(title);
			game.setPublisher(publisher);
			game.setRelease(LocalDateTime.parse(release));
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
			pw.write("CREATED");
		}
	}

	public void retrieve(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("gameId");

		PrintWriter pw = response.getWriter();

		if(id == null || id.isEmpty()) {
			Collection<Game> games = gameRepository.findAll();
			games.forEach(game -> pw.write(game.toString() + "\n"));
		}else {
			Game game = gameRepository.findById(Long.parseLong(id));
			if(game == null) {
				pw.write("NO CONTENT");
			}else {
				pw.write(game.getId() + "\n");
				pw.write(game.getTitle() + "\n");
				pw.write(game.getPublisher() + "\n");
				pw.write(game.getRelease() + "\n");
				pw.write(game.getSynopsis() + "\n");
				for(Map.Entry<String, String> entry : game.getRequirement().entrySet()) {
					pw.write(entry.getKey() + ":" + entry.getValue() + "\n");
				}
				for(String plat : game.getPlatform()) {
					pw.write(plat + "\n");
				}
			}
		}
	}

	public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("gameId");

		PrintWriter pw = response.getWriter();

		if(id == null || id.isEmpty()){
			pw.write("ID CANT BE EMPTY");
		}else {
			gameRepository.deleteById(Long.parseLong(id));
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
