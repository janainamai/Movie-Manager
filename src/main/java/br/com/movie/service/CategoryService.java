package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.enums.Category;
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

    public Category findByDescription(String description) {
        return categoryRepository.findByDescription(description.toUpperCase())
                .orElseThrow(() -> new BadRequestException(CATEGORY_NOT_FOUND));
    }

    public Category save(Category category) {
        Optional<Category> optional = categoryRepository.findByDescription(category.getDescription());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a category with this description");
        }

        category.setDescription(category.getDescription().toUpperCase());
        return categoryRepository.save(category);
    }

    public Category replace(Category category) {
        Optional<Category> optional = categoryRepository.findById(category.getId());

        if (optional.isPresent()) {
            return categoryRepository.save(category);
        } else {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }
    }

}