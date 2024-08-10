package model.dto.Game;

import java.time.LocalDateTime;

import fileSystem.Writable;

public class GameDto implements Writable<GameDto> {

	private Long id;
	private String title;
	private String imageName;
	private String publisher;
	private LocalDateTime release;
	private String synopsis;
	private Long categoryId;

	public GameDto() {}

	public GameDto(Long id, String title, String imageName, String publisher, LocalDateTime release, String synopsis, Long categoryId) {
		this.id = id;
		this.title = title;
		this.imageName = imageName;
		this.publisher = publisher;
		this.release = release;
		this.synopsis = synopsis;
		this.categoryId = categoryId;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getImageName() {
		return imageName;
	}

	public String getPublisher() {
		return publisher;
	}

	public LocalDateTime getRelease() {
		return release;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	@Override
	public GameDto fromString(String... args) {
		return new GameDto(Long.parseLong(args[0]), args[1], args[2], args[3], LocalDateTime.parse(args[4]), args[5], Long.parseLong(args[6]));
	}

	@Override
	public String toString() {
		return String.format("%d;%s;%s;%s;%s;%s;%d", this.id, this.title, this.imageName, this.publisher, this.release, this.synopsis, this.categoryId);
	}

}
