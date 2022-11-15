package com.fond.app.service;

import com.fond.app.dto.UserDto;
import com.fond.app.models.User;
import com.fond.app.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return UserDto.from(users);
    }
    @Override
    public UserDto getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return UserDto.from(usersRepository.getUserByEmail(currentUserName));
        }
        return null;
    }


    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(usersRepository.getReferenceById(userId));
    }
}
