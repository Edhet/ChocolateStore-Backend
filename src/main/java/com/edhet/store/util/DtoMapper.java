package com.edhet.store.util;

import com.edhet.store.category.Category;
import com.edhet.store.category.CategoryDTO;
import com.edhet.store.exception.errors.BadRequestException;
import com.edhet.store.product.Product;
import com.edhet.store.product.ProductDTO;
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

    public UserDTO userToDto(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getSurname(),
                user.getEmail(),
                user.getBirthDate(),
                user.getGender(),
                user.getOrders(),
                (user.getPreferredCategory() != null) ? user.getPreferredCategory().getName() : null,
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
}
