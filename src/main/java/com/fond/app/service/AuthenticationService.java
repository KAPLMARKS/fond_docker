package com.fond.app.service;

import com.fond.app.dto.UserDto;
import com.fond.app.models.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    User getUser(Authentication authentication);
}
