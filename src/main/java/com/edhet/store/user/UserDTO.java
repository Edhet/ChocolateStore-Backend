package com.edhet.store.user;

import com.edhet.store.order.BuyOrderDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        String firstName,
        String surname,
        String email,
        LocalDate birthDate,
        Gender gender,
        List<BuyOrderDTO> buyOrders,
        String prefferedCategory,
        LocalDateTime creationTimestamp
        ) {
}
