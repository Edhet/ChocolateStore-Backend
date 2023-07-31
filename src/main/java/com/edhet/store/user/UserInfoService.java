package com.edhet.store.user;

import com.edhet.store.security.SecurityConstants;
import com.edhet.store.security.jwt.JwtService;
import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoService {
    private final JwtService jwtService;
    private final UserService userService;
    private final DtoMapper dtoMapper;

    public UserDTO getUserInfo(String authHeader) {
        final String jwtToken = authHeader.substring(SecurityConstants.TOKEN_PREFIX.length() - 1);
        final String email = jwtService.extractEmail(jwtToken);

        User user = userService.getUser(email);
        return dtoMapper.userToDto(user);
    }
}
