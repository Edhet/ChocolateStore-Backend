package com.edhet.store.util;

import com.edhet.store.address.Address;
import com.edhet.store.address.AddressDTO;
import com.edhet.store.address.AddressRequest;
import com.edhet.store.category.Category;
import com.edhet.store.category.CategoryDTO;
import com.edhet.store.error.exceptions.BadRequestException;
import com.edhet.store.order.BuyOrder;
import com.edhet.store.order.BuyOrderDTO;
import com.edhet.store.product.Product;
import com.edhet.store.product.ProductDTO;
import com.edhet.store.security.login.JwtResponse;
import com.edhet.store.security.registration.RegistrationRequest;
import com.edhet.store.user.User;
import com.edhet.store.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DtoMapper {
    private final PasswordEncoder passwordEncoder;

    public User registrationRequestToUser(RegistrationRequest request) throws BadRequestException {
        User user;
        try {
            user = new User(
                    request.firstName(),
                    request.surname(),
                    request.email(),
                    passwordEncoder.encode(request.password()),
                    request.birthDate(),
                    request.gender()
            );
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("password field is null");
        } catch (NullPointerException e) {
            String field = e.getMessage().split(" ")[0];
            throw new BadRequestException(field + " field is null");
        }
        return user;
    }

    public Address requestToAddress(AddressRequest request, User user) {
        Long expectedNumber = Shared.stringToLongParsing(request.number());

        try {
            return new Address(
                    request.country(),
                    request.state(),
                    request.city(),
                    request.postalCode(),
                    request.road(),
                    expectedNumber,
                    user
            );
        } catch (NullPointerException e) {
            String field = e.getMessage().split(" ")[0];
            throw new BadRequestException(field + " field is null");
        }
    }

    public UserDTO userToDto(User user) {
        List<BuyOrderDTO> buyOrderDTOs = user.getBuyOrders().stream().map(this::buyOrderToDto).toList();
        AddressDTO addressDTO = (user.getAddress() != null) ? addressToDto(user.getAddress()) : null;
        String preferredCategory = (user.getPreferredCategory() != null) ? user.getPreferredCategory().getName() : null;

        return new UserDTO(
                user.getFirstName(),
                user.getSurname(),
                user.getEmail(),
                user.getBirthDate(),
                user.getGender(),
                addressDTO,
                buyOrderDTOs,
                preferredCategory,
                user.getCreationTimestamp()
        );
    }

    public ProductDTO productToDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory().getName(),
                product.getPrice()
        );
    }

    public CategoryDTO categoryToDto(Category category) {
        List<ProductDTO> productDTOs = category.getProducts().stream().map(this::productToDto).toList();

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                productDTOs
        );
    }

    public BuyOrderDTO buyOrderToDto(BuyOrder buyOrder) {
        ProductDTO productDTO = productToDto(buyOrder.getProduct());

        return new BuyOrderDTO(
                buyOrder.getId(),
                productDTO,
                buyOrder.getAmount()
        );
    }

    public AddressDTO addressToDto(Address address) {
        return new AddressDTO(
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getPostalCode(),
                address.getRoad(),
                address.getNumber()
        );
    }

    public JwtResponse jwtToResponse(String jwt) {
        return new JwtResponse(jwt);
    }
}
