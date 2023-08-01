package com.edhet.store.category;

import com.edhet.store.exception.errors.EntityNotFoundException;
import com.edhet.store.exception.errors.UniqueDatabaseFieldException;
import com.edhet.store.product.Product;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(String name) {
        if (categoryRepository.existsByName(name))
            throw new UniqueDatabaseFieldException("A category called " + name + " already exists");
        Category newCategory = new Category(name, new ArrayList<>());
        categoryRepository.save(newCategory);
    }

    public Category getCategory(String name) throws EntityNotFoundException {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category with name " + name + " not found"));
    }

    public Category getCategory(Long id) throws EntityNotFoundException {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    public void addProductToCategory(Product product, @NonNull Category category) {
        category.addProduct(product);
        categoryRepository.save(category);
    }

    public void removeProductFromCategory(Product product, @NonNull Category category) {
        category.removeProduct(product);
        categoryRepository.save(category);
    }
}
