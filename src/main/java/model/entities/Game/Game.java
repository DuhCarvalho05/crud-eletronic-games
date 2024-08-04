package model.entities.Game;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public class Game {
	
	private Long id;
	private String title;
	private String publisher;
	private LocalDateTime release;
	private String synopsis;
	private Map<String, String> requirement;
	private Collection<String> platform;
	
	public Game() {}

	public Game(Long id, String title, String publisher, LocalDateTime release, String synopsis,
			Map<String, String> requirement, Collection<String> platform) {
		super();
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.release = release;
		this.synopsis = synopsis;
		this.requirement = requirement;
		this.platform = platform;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDateTime getRelease() {
		return release;
	}

	public void setRelease(LocalDateTime release) {
		this.release = release;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Map<String, String> getRequirement() {
		return requirement;
	}

	public void setRequirement(Map<String, String> requirement) {
		this.requirement = requirement;
	}

	public Collection<String> getPlatform() {
		return platform;
	}

	public void setPlatform(Collection<String> platform) {
		this.platform = platform;
	}

	
	
}
