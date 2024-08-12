package controller.pages.ADMIN.Game;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.entities.Category.Category;
import model.entities.Game.Game;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;
import utils.PathFile;

/**
 * Servlet implementation class RegisterGameServlet
 */
@WebServlet("/register-game/*")
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class RegisterGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String uploadPath = PathFile.getInstance().getPath() + "/images/";

	private final GameRepository gameRepository;
	private final CategoryRepository categoryRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterGameServlet() {
        super();
        this.categoryRepository = new CategoryRepository();
        this.gameRepository = new GameRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("categories", categoryRepository.findAll());
		getServletContext().getRequestDispatcher("/ADMIN/register-game.jsp").forward(request, response);
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

		String url = "/ADMIN/game-list.jsp";
		String msg = "error";

		if(title.isEmpty() || publisher.isEmpty() || release.isEmpty() || synopsis.isEmpty() || categoryId.isEmpty() || requirementNotMapped.isEmpty() || platformNotMapped.isEmpty()) {
			msg = "empty-fields";
		}else {
			Game game = new Game();
			game.setTitle(title);
			game.setPublisher(publisher);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			game.setRelease(LocalDateTime.parse(release, formatter));
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

			msg = "CREATED";
		}

		getServletContext().setAttribute("msg", msg);
		getServletContext().getRequestDispatcher(url).forward(request, response);
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
