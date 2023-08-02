package com.edhet.store.product;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Boolean existsByName(String name);

    List<Product> findAllByCategory(@NonNull String category);

}
