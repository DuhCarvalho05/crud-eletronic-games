package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.services.RatingService;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet("/rating")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final RatingService ratingService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
        super();
        this.ratingService = new RatingService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ratingService.retrieve(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ratingService.register(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ratingService.remove(request, response);
	}

}
