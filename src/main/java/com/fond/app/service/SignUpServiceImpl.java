package com.fond.app.service;

import com.fond.app.forms.SignUpForm;
import com.fond.app.models.Role;
import com.fond.app.models.User;
import com.fond.app.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean signUp(SignUpForm form) {
        String rawPassword = form.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);
        Optional<User> userFromDB = usersRepository.findByEmail(form.getEmail());
        if(!userFromDB.isPresent()){
            User user = User.builder()
                    .email(form.getEmail())
                    .hashPassword(hashPassword)
                    .role(Role.USER)
                    .build();

            usersRepository.save(user);
            return true;
        }
        return false;

    }
}
