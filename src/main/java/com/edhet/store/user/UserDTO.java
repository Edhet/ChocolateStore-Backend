package com.edhet.store.user;

import com.edhet.store.address.AddressDTO;
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
        AddressDTO address,
        List<BuyOrderDTO> buyOrders,
        String preferredCategory,
        LocalDateTime creationTimestamp
) {
}
