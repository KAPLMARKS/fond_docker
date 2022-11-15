package com.fond.app.repositories;

import com.fond.app.dto.UserDto;
import com.fond.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User getUserByEmail(String email);
}


