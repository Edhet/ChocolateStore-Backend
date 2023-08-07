package com.edhet.store.address;

public record AddressRequest(
        String country,
        String state,
        String city,
        String postalCode,
        String road,
        String number
) {
}
