package com.edhet.store.address;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PutMapping
    public void setAddress(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                           @RequestBody AddressRequest request) {
        addressService.setAddress(authHeader, request);
    }
}
