package repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import fileSystem.FileInterpreter;
import fileSystem.FileManagement;
import infra.Category.CategoryFileConverter;
import model.dto.Category.CategoryDto;
import model.entities.Category.Category;
import repository.IRepository;

public class CategoryRepository implements IRepository<Category, Long> {

	private static Long SEQUENCE = 0L;
	
	private String categoryFileName = "category.csv";

	private final FileManagement fileManagement;
    private final FileInterpreter fileInterpreter;
    private final CategoryFileConverter categoryFileConverter;

	public CategoryRepository() {
		this.fileManagement = new FileManagement();
		this.fileInterpreter = new FileInterpreter();
		this.categoryFileConverter =  new CategoryFileConverter();
	}

	@Override
	public void save(Category category) {
		if(category.getId() == null) {
			category.setId(++SEQUENCE);
		}

		delete(category.getId());
		CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());
		fileManagement.write(categoryDto, categoryFileName);

	}

	@Override
	public void saveAll(Collection<Category> collection) {
		for(Category category : collection) {
			save(category);
		}

	}

	@Override
	public Category find(Long identifier) {
		Collection<Category> categories = findAll();
		for(Category category : categories) {
			if(category.getId().equals(identifier)) {
				return category;
			}
		}
		return null;
	}

	@Override
	public Collection<Category> findAll() {
		Collection<CategoryDto> categoriesDto = categoryFileConverter.all(fileInterpreter.interpret(fileManagement.read(categoryFileName), CategoryDto.class));

		Collection<Category> categories = new ArrayList<>();
		categoriesDto.forEach( dto -> categories.add(generate(dto)) );

		return categories;
	}

	@Override
	public Collection<Category> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long identifier) {
		Collection<Category> categories = findAll();
		fileManagement.clear(categoryFileName);
		categories.removeIf( category -> category.getId().equals(identifier) );
		saveAll(categories);
	}

	private Category generate(CategoryDto categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getName());
	}

}
