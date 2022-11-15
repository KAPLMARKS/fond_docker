package com.fond.app.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fond_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(
            mappedBy="user",
            fetch = FetchType.EAGER
    )
    private Set<Project> projects;
}
