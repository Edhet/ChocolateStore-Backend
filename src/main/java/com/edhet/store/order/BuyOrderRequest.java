package com.edhet.store.order;

public record BuyOrderRequest(
        String productId,
        String amount
) {
}
