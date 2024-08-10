package model.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Category.Category;
import repository.impl.CategoryRepository;

public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService() {
		this.categoryRepository = new CategoryRepository();
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("categoryId");

		PrintWriter pw = response.getWriter();

		if(id == null || id.isEmpty()) {
			Collection<Category> categories = categoryRepository.findAll();
			categories.forEach( category -> pw.write(category.toString() + "\n") );
		}else {
			Category category = categoryRepository.findById(Long.parseLong(id));
			if(category == null) {
				pw.write("NO CONTENT");
			}else {
				pw.write(category.toString());
			}
		}
	}

	public void retrieve(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");

		PrintWriter pw = response.getWriter();

		if(name.isEmpty()) {
			pw.write("FIELDS CANT BE EMPTY");
		}else {
			Category category = new Category();
			category.setName(name);
			categoryRepository.save(category);
			pw.write("CREATED");
		}
	}

	public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("categoryId");

		PrintWriter pw = response.getWriter();

		if(id == null || id.isEmpty()){
			pw.write("ID CANT BE EMPTY");
		}else {
			categoryRepository.deleteById(Long.parseLong(id));
		}
	}
}
