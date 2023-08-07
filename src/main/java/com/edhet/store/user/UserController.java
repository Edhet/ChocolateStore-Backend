package com.edhet.store.user;

import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/info")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final DtoMapper dtoMapper;

    @GetMapping
    public UserDTO getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return dtoMapper.userToDto(userService.getUserFromJwt(authHeader));
    }

    @PutMapping("/category")
    public void setPreferredCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                     @RequestBody String categoryName) {
        userService.setPreferredCategory(authHeader, categoryName);
    }
}
