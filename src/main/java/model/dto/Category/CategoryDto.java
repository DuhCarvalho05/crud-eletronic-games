package model.dto.Category;

import fileSystem.ObjectConvert;

public class CategoryDto implements ObjectConvert<CategoryDto> {

	private Long id;
	private String name;

	public CategoryDto() {}

	public CategoryDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public CategoryDto fromString(String... args) {
		return new CategoryDto(Long.parseLong(args[0]), args[1]);
	}

	@Override
	public String toString() {
		return String.format("%s;%s", id, name);
	}

}
