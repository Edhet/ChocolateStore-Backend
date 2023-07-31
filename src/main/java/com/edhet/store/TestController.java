package com.edhet.store;

import com.edhet.store.user.UserDTO;
import com.edhet.store.user.UserService;
import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final UserService userService;
    private final DtoMapper dtoMapper;

    @GetMapping
    public UserDTO get(@RequestParam Long id) {
        return dtoMapper.userToDto(userService.getUser(id));
    }
}
