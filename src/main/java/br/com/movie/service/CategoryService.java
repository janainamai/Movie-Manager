package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Category;
import br.com.movie.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final String CATEGORY_NOT_FOUND = "Category not found";

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(CATEGORY_NOT_FOUND));
    }

    public Category findByDescription(String description) {
        return categoryRepository.findByDescription(description.toUpperCase())
                .orElseThrow(() -> new BadRequestException(CATEGORY_NOT_FOUND));
    }

    public Category save(Category category) {
        Optional<Category> optional = categoryRepository.findByDescription(category.getDescription().toUpperCase());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a category with this description");
        }

        category.setDescription(category.getDescription().toUpperCase());
        return categoryRepository.save(category);
    }

    public Category replace(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            category.setDescription(category.getDescription().toUpperCase());
            return this.save(category);
        } else {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }
    }

}