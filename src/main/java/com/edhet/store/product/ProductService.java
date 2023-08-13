package com.edhet.store.product;

import com.edhet.store.error.exceptions.BadRequestException;
import com.edhet.store.error.exceptions.EntityNotFoundException;
import com.edhet.store.util.Shared;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProductsWithCount(String count) {
        int expectedCount = (int) Shared.stringToLongParsing(count);
        Pageable limit = PageRequest.of(0, expectedCount);
        return productRepository.findAll(limit);
    }

    public Product getProduct(Long id) throws EntityNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum produto com id: " + id));
    }

    public Product getProductFromRequest(String id) throws BadRequestException {
        long expectedId = Shared.stringToLongParsing(id);
        return getProduct(expectedId);
    }
}
