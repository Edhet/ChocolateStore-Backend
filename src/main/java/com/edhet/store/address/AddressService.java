package com.edhet.store.address;

import com.edhet.store.user.User;
import com.edhet.store.user.UserService;
import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private final UserService userService;
    private final DtoMapper dtoMapper;
    private final AddressRepository addressRepository;

    public void setAddress(String authHeader, AddressRequest request) {
        User userFromJwt = userService.getUserFromJwt(authHeader);
        Address address = dtoMapper.requestToAddress(request, userFromJwt);

        boolean shouldReplaceAddress = userFromJwt.getAddress() != null;
        if (shouldReplaceAddress)
            addressRepository.delete(userFromJwt.getAddress());

        userFromJwt.setAddress(address);
        addressRepository.save(address);
    }
}
