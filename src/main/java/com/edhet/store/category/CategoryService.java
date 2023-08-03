package com.edhet.store.category;

import com.edhet.store.exception.errors.EntityNotFoundException;
import com.edhet.store.exception.errors.UniqueDatabaseFieldException;
import com.edhet.store.product.ProductDTO;
import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
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
}
