package com.edhet.store.product;

import com.edhet.store.error.exceptions.BadRequestException;
import com.edhet.store.error.exceptions.EntityNotFoundException;
import com.edhet.store.util.Shared;
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

    public Product getProduct(Long id) throws EntityNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product with id: " + id));
    }

    public Product getProductFromRequest(String id) throws BadRequestException {
        long expectedId = Shared.getIdFromStringRequest(id);
        return getProduct(expectedId);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
