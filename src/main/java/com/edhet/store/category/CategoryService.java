package com.edhet.store.category;

import com.edhet.store.exception.errors.CategoryNotFound;
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
        Category newCategory = new Category(name, new ArrayList<>());
        categoryRepository.save(newCategory);
    }

    public Category getCategory(String name) throws CategoryNotFound {
        return categoryRepository
                .getCategoryByName(name)
                .orElseThrow(() -> new CategoryNotFound("Category with name " + name + " not found"));
    }

    public Category getCategory(Long id) throws CategoryNotFound {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category with id " + id + " not found"));
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
