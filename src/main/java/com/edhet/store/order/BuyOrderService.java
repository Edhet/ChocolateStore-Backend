package com.edhet.store.order;

import com.edhet.store.product.Product;
import com.edhet.store.product.ProductService;
import com.edhet.store.user.User;
import com.edhet.store.user.UserService;
import com.edhet.store.util.Shared;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuyOrderService {

    private final BuyOrderRepository buyOrderRepository;
    private final UserService userService;
    private final ProductService productService;

    public void createBuyingOrder(String authHeader, BuyOrderRequest request) {
        Long expectedProductId = Shared.stringToLongParsing(request.productId());
        Long expectedAmountValue = Shared.stringToLongParsing(request.amount());

        User userFromJwt = userService.getUserFromJwt(authHeader);
        Product product = productService.getProduct(expectedProductId);

        BuyOrder buyOrder = new BuyOrder(product, expectedAmountValue, userFromJwt);
        buyOrderRepository.save(buyOrder);
    }
}
