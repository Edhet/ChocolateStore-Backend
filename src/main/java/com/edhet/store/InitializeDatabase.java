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
                new Category("Meio Amargo"),
                new Category("Branco"),
                new Category("Ao Leite")
        );
        List<Product> products = List.of(
                new Product("Chocolate Agridoce", categories.get(0), BigDecimal.valueOf(15.99)),
                new Product("Chocolate Rico", categories.get(0), BigDecimal.valueOf(19.99)),
                new Product("Chocolate Marfim", categories.get(1), BigDecimal.valueOf(12.99)),
                new Product("Chocolate Baunilha", categories.get(1), BigDecimal.valueOf(5.99)),
                new Product("Chocolate Cremoso", categories.get(2), BigDecimal.valueOf(9.99)),
                new Product("Chocolate Doce", categories.get(2), BigDecimal.valueOf(4.99))
        );

        if (categoryRepository.findAll().isEmpty())
            categoryRepository.saveAll(categories);
        if (productRepository.findAll().isEmpty())
            productRepository.saveAll(products);
    }
}
