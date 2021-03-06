package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Category;
import br.com.movie.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    private static final String CATEGORY_NOT_FOUND = "Category not found";

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> list() {
        return categoryRepository.findAll()
                .stream()
                .sorted(comparing(Category::getDescription))
                .collect(toList());
    }

    public List<Category> findByDescription(String description) {
        return categoryRepository.findByDescriptionContainingIgnoreCase(description)
                .orElseThrow(() -> new BadRequestException(CATEGORY_NOT_FOUND));
    }

    public Category save(Category category) {
        Optional<Category> optional = categoryRepository.findByDescriptionIgnoreCase(category.getDescription());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a category with this description");
        }

        return categoryRepository.save(category);
    }

    public Category replace(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            return this.save(category);
        } else {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }
    }

}