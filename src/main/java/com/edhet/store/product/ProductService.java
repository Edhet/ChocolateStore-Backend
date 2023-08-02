package com.edhet.store.product;

import com.edhet.store.exception.errors.BadRequestException;
import com.edhet.store.exception.errors.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsInCategory(String categoryName) {
        return productRepository.findAllByCategory(categoryName);
    }

    public Product getProduct(Long id) throws EntityNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product with id: " + id));
    }

    public Product getProductFromRequest(String id) throws BadRequestException {
        long expectedId;
        try {
            expectedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException(e.getMessage());
        }
        return getProduct(expectedId);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
