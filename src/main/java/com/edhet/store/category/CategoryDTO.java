package com.edhet.store.category;

import com.edhet.store.product.ProductDTO;

import java.util.List;

public record CategoryDTO(
        Long id,
        String name,
        List<ProductDTO> products
) {
}
