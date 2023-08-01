package com.edhet.store.product;

import com.edhet.store.category.CategoryService;
import com.edhet.store.exception.errors.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsInCategory(String categoryName) {
        return categoryService
                .getCategory(categoryName)
                .getProducts();
    }

    public Product getProduct(Long id) throws EntityNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product with id: " + id));
    }
}
