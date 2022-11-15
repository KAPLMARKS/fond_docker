package com.fond.app.dto;

import com.fond.app.models.Project;
import com.fond.app.models.Role;
import com.fond.app.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String email;
    private Set<Project> projects;
    private Role role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .projects(user.getProjects())
                .role(user.getRole())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
