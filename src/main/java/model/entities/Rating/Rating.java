package model.entities.Rating;

import java.time.LocalDateTime;

import model.entities.Game.Game;
import model.entities.User.User;

public class Rating {

	private Long id;
	private String description;
	private int stars;
	private LocalDateTime createdAt;
	private User user;
	private Game game;

	public Rating() {}

	public Rating(Long id, String description,  int stars, LocalDateTime createdAt, User user, Game game) {
		this.id = id;
		this.description = description;
		this.stars = stars;
		this.createdAt = createdAt;
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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
