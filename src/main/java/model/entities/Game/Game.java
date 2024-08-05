package model.entities.Game;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import fileSystem.ObjectConvert;

public class Game implements ObjectConvert<Game> {
	
	private Long id;
	private String title;
	private String publisher;
	private LocalDateTime release;
	private String synopsis;
//	private Category category;
//	private Map<String, String> requirement;
//	private Collection<String> platform;
//	private Collection<Image> image;
	
	public Game() {}
	
	private Game(Long id, String title, String publisher, LocalDateTime release, String synopsis) {
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.release = release;
		this.synopsis = synopsis;
	}

	public Game(Long id, String title, String publisher, LocalDateTime release, String synopsis, Category category,
			Map<String, String> requirement, Collection<String> platform, Collection<Image> image) {
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.release = release;
		this.synopsis = synopsis;
//		this.category = category;
//		this.requirement = requirement;
//		this.platform = platform;
//		this.image = image;
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
	
//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}
//
//	public Map<String, String> getRequirement() {
//		return requirement;
//	}
//
//	public void setRequirement(Map<String, String> requirement) {
//		this.requirement = requirement;
//	}
//
//	public Collection<String> getPlatform() {
//		return platform;
//	}
//
//	public void setPlatform(Collection<String> platform) {
//		this.platform = platform;
//	}
//
//	public Collection<Image> getImage() {
//		return image;
//	}
//
//	public void setImage(Collection<Image> image) {
//		this.image = image;
//	}

	@Override
	public Game fromString(String... args) {
		return new Game(Long.parseLong(args[0]), args[1], args[2], LocalDateTime.parse(args[3]), args[4]);
	}

	@Override
	public String toString() {
		return String.format("%d;%s;%s;%s;%s", this.id, this.title, this.publisher, this.release, this.synopsis);
	}
	
	
}
