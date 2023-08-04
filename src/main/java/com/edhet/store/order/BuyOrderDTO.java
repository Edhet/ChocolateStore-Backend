package com.edhet.store.order;

import com.edhet.store.product.ProductDTO;

public record BuyOrderDTO(
        Long id,
        ProductDTO product,
        Long amount
) {
}
