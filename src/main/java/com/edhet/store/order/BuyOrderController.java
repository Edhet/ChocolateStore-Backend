package com.edhet.store.order;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class BuyOrderController {

    private final BuyOrderService buyOrderService;

    @PostMapping("/buy")
    public void createBuyingOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                     @RequestBody BuyOrderRequest request) {
    buyOrderService.createBuyingOrder(authHeader, request);
    }
}
