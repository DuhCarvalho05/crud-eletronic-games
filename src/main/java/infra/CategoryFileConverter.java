package infra;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.dto.Category.CategoryDto;

public class CategoryFileConverter implements FileConvert<CategoryDto> {

	@Override
	public CategoryDto generate(String... args) {
		return new CategoryDto().fromString(args);
	}

	@Override
	public Collection<CategoryDto> all(Collection<String[]> dataset) {
		Collection<CategoryDto> categoriesDto = new ArrayList<>();
		dataset.forEach( category -> categoriesDto.add(generate(category)) );
		return categoriesDto;
	}

}
