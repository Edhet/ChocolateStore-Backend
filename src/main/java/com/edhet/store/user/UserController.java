package com.edhet.store.user;

import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
