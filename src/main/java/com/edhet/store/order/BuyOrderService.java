package com.edhet.store.order;

import com.edhet.store.error.exceptions.BadRequestException;
import com.edhet.store.error.exceptions.EntityNotFoundException;
import com.edhet.store.product.Product;
import com.edhet.store.product.ProductService;
import com.edhet.store.user.User;
import com.edhet.store.user.UserService;
import com.edhet.store.util.Shared;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuyOrderService {

    private final BuyOrderRepository buyOrderRepository;
    private final UserService userService;
    private final ProductService productService;

    public void createBuyingOrder(String authHeader, BuyOrderRequest request) {
        long expectedProductId = Shared.stringToLongParsing(request.productId());
        long expectedAmountValue = Shared.stringToLongParsing(request.amount());

        User userFromJwt = userService.getUserFromJwt(authHeader);
        Product product = productService.getProduct(expectedProductId);

        BuyOrder buyOrder = new BuyOrder(product, expectedAmountValue, userFromJwt);
        buyOrderRepository.save(buyOrder);
    }

    public void deleteBuyingOrder(String authHeader, String requestId) throws EntityNotFoundException, BadRequestException {
        long expectedProductId = Shared.stringToLongParsing(requestId);

        BuyOrder selectedBuyOrder = buyOrderRepository
                .findById(expectedProductId)
                .orElseThrow(() -> new EntityNotFoundException("No buy order with id: " + expectedProductId));

        User userFromJwt = userService.getUserFromJwt(authHeader);

        if (!userFromJwt.getBuyOrders().contains(selectedBuyOrder))
            throw new BadRequestException("buy order is for another user");

        buyOrderRepository.delete(selectedBuyOrder);
    }

    public void buyCart(String authHeader) {
        User userFromJwt = userService.getUserFromJwt(authHeader);
        List<BuyOrder> userOrders = userFromJwt.getBuyOrders();
        buyOrderRepository.deleteAll(userOrders);
    }
}
