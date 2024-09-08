package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
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
import model.payload.Category.CategoryRequest;
import repository.impl.CategoryRepository;
import repository.impl.GameRepository;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category/*")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryRepository categoryRepository;
	private final GameRepository gameRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		this.categoryRepository = new CategoryRepository();
		this.gameRepository = new GameRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryIdRequest = "";
		String categoryIdParameter = request.getParameter("categoryId");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (categoryIdParameter != null && !categoryIdParameter.isEmpty()) {
			try {
				Long id = Long.parseLong(categoryIdParameter);

				Category category = categoryRepository.findById(id);

				if (category != null) {
					String json = new Gson().toJson(category);
					response.getWriter().write(json);
					response.setStatus(200);
				}

			} catch (Exception e) {
				response.setStatus(404);
			}
		} else {
			if (request.getPathInfo() != null && !request.getPathInfo().isEmpty()) {
				categoryIdRequest = request.getPathInfo().substring(1);
				
				try {
					Long id = Long.parseLong(categoryIdRequest);
					Category category = categoryRepository.findById(id);
					
					if (category != null) {
						List<Game> gamesOfThisCategory = (List<Game>) gameRepository.findAllByCategory(category.getId());
						String json = new Gson().toJson(gamesOfThisCategory);
						response.getWriter().write(json);
						response.setStatus(200);
					} else {
						response.setStatus(404);
					}
				}catch(Exception e) {
					response.setStatus(404);
				}
			} else {
				List<Category> categories = (List<Category>) categoryRepository.findAll();
				String json = new Gson().toJson(categories);
				response.getWriter().write(json);
				response.setStatus(200);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();

		CategoryRequest categoryRequest = gson.fromJson(reader, CategoryRequest.class);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (categoryRequest.name() != null && !categoryRequest.name().isEmpty()) {

			Category categoryExistent = categoryRepository.findByName(categoryRequest.name());
			if (categoryExistent != null) {
				response.setStatus(409);
			} else {
				Category category = new Category();
				category.setName(categoryRequest.name());

				categoryRepository.save(category);

				response.setStatus(201);
			}
		} else {
			response.setStatus(400);
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(categoryId != null && !categoryId.isEmpty()) {
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();

			CategoryRequest categoryRequest = gson.fromJson(reader, CategoryRequest.class);
			
			if (categoryRequest.name() != null && !categoryRequest.name().isEmpty()) {

				Category categoryExistent = categoryRepository.findByName(categoryRequest.name());
				if (categoryExistent != null) {
					response.setStatus(409);
				} else {
					
					try {
						Long id = Long.parseLong(categoryId);
						Category category = categoryRepository.findById(id);

						if (category != null) {
							category.setName(categoryRequest.name());
							categoryRepository.save(category);
							response.setStatus(200);
						}
					}catch(Exception e) {
						response.setStatus(404);
					}
					
					
				}
			} else {
				response.setStatus(400);
			}
			
		}else {
			response.setStatus(404);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryId = req.getParameter("categoryId");
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		if(categoryId != null && !categoryId.isEmpty()) {
			try {
				Long id = Long.parseLong(categoryId);
				Category category = categoryRepository.findById(id);

				if (category != null) {
					
					Collection<Game> gamesWithThisCategory = gameRepository.findAllByCategory(id);
					
					if(gamesWithThisCategory.isEmpty()) {					
						categoryRepository.deleteById(id);
						resp.setStatus(200);
					}else {
						resp.setStatus(406);
					}
					
				}else {
					resp.setStatus(404);
				}

			} catch (NumberFormatException e) {
				resp.setStatus(404);
			}
		}
	}

}
