package com.edhet.store.address;

public record AddressDTO(
        String country,
        String state,
        String city,
        String postalCode,
        String road,
        Long number
) {
}
