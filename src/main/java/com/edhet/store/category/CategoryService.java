package com.edhet.store.category;

import com.edhet.store.error.exceptions.BadRequestException;
import com.edhet.store.error.exceptions.EntityNotFoundException;
import com.edhet.store.util.Shared;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<String> getAllCategoryNames() {
        return categoryRepository
                .findAll()
                .stream().map(Category::getName)
                .toList();
    }

    public Category getCategoryFromRequest(String id) throws BadRequestException {
        long expectedId = Shared.stringToLongParsing(id);
        return getCategory(expectedId);
    }

    public Category getCategory(String name) throws EntityNotFoundException {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma categoria com nome: " + name));
    }

    public Category getCategory(Long id) throws EntityNotFoundException {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma categoria com id: " + id));
    }
}
