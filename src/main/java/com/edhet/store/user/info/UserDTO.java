package com.edhet.store.user.info;

import com.edhet.store.order.BuyingOrder;
import com.edhet.store.category.Category;
import com.edhet.store.user.Gender;

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
