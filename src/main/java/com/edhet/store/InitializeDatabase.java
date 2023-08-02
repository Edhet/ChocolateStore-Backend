package com.edhet.store;

import com.edhet.store.category.Category;
import com.edhet.store.category.CategoryRepository;
import com.edhet.store.product.Product;
import com.edhet.store.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class InitializeDatabase implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Category> categories = List.of(
                new Category("Dark Chocolate"),
                new Category("White Chocolate"),
                new Category("Milk Chocolate")
        );
        List<Product> products = List.of(
                new Product("Bittersweet Chocolate", categories.get(0).getName(), BigDecimal.valueOf(15.99)),
                new Product("Rich Chocolate", categories.get(0).getName(), BigDecimal.valueOf(19.99)),
                new Product("Ivory Chocolate", categories.get(1).getName(), BigDecimal.valueOf(12.99)),
                new Product("Vanilla Chocolate", categories.get(1).getName(), BigDecimal.valueOf(5.99)),
                new Product("Creamy Chocolate", categories.get(2).getName(), BigDecimal.valueOf(9.99)),
                new Product("Sweet Chocolate", categories.get(2).getName(), BigDecimal.valueOf(4.99))
        );

        if (categoryRepository.findAll().isEmpty())
            categoryRepository.saveAll(categories);
        if (productRepository.findAll().isEmpty())
            productRepository.saveAll(products);
    }
}
