package com.edhet.store.user;

import com.edhet.store.order.BuyingOrder;
import com.edhet.store.product.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        String firstName,
        String surname,
        String email,
        LocalDate birthDate,
        Gender gender,
        List<BuyingOrder> orders,
        Category prefferedCategory,
        LocalDateTime creationTimestamp
        ) {
}
