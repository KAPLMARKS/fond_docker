package com.fond.app.service;

import com.fond.app.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    UserDto getCurrentUser();

    UserDto getUser(Long userId);
}

