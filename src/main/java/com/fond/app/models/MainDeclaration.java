package com.fond.app.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "main_declaration")
public class MainDeclaration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameDeclaration;
    @OneToMany(
            mappedBy="mainDeclaration",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<DopDeclaration> dopDeclarationSet;
}
