package com.edhet.store.user.info;

import com.edhet.store.security.SecurityConstants;
import com.edhet.store.security.jwt.JwtService;
import com.edhet.store.user.User;
import com.edhet.store.user.UserService;
import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoService {
    private final UserService userService;
    private final DtoMapper dtoMapper;

    public UserDTO getUserInfo(String authHeader) {
        final String jwtToken = authHeader.substring(SecurityConstants.TOKEN_PREFIX.length() - 1);
        User user = userService.getUserFromJwt(jwtToken);
        return dtoMapper.userToDto(user);
    }
}
