package com.edhet.store.product;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        String category,
        BigDecimal price
) {
}
