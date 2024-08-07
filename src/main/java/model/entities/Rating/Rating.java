package model.entities.Rating;

import model.entities.Game.Game;
import model.entities.User.User;

public class Rating {

	private Long id;
	private String description;
	private User user;
	private Game game;

	public Rating() {}

	public Rating(Long id, String description, User user, Game game) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.game = game;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
