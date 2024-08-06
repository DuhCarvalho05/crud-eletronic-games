package model.dto.Game;

import java.time.LocalDateTime;

import fileSystem.ObjectConvert;

public class GameDto implements ObjectConvert<GameDto> {

	private Long id;
	private String title;
	private String publisher;
	private LocalDateTime release;
	private String synopsis;
	
	public GameDto() {}

	public GameDto(Long id, String title, String publisher, LocalDateTime release, String synopsis) {
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.release = release;
		this.synopsis = synopsis;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
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

	@Override
	public GameDto fromString(String... args) {
		return new GameDto(Long.parseLong(args[0]), args[1], args[2], LocalDateTime.parse(args[3]), args[4]);
	}

	@Override
	public String toString() {
		return String.format("%d;%s;%s;%s;%s", this.id, this.title, this.publisher, this.release, this.synopsis);
	}
	
}
