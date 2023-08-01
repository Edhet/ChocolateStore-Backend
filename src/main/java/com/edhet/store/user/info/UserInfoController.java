package com.edhet.store.user.info;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/info")
@AllArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping
    public UserDTO getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return userInfoService.getUserInfo(authHeader);
    }
}
