package com.fond.app.service;

import com.fond.app.dto.UserDto;
import com.fond.app.models.User;
import com.fond.app.repositories.UsersRepository;
import com.fond.app.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public User getUser(Authentication authentication) {
        UserDetailsImpl userDetail = (UserDetailsImpl) authentication.getPrincipal();
        Long id = userDetail.getUser().getId();
        Optional<User> userModelOptional = usersRepository.findById(id);
        if (userModelOptional.isPresent()) {
            return userModelOptional.get();
        } else throw new UsernameNotFoundException("User with id: " + id + " not found");
    }
}
