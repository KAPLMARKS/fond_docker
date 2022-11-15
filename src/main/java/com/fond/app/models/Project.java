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
@Table(name = "projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProject;
    private String description;
    @Column(name = "inn")
    private String inn;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "main_declaration_id")
    private MainDeclaration mainDeclaration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dop_declaration_id")
    private DopDeclaration dopDeclaration;
    private String keywords;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(
            mappedBy="project",
            fetch = FetchType.EAGER
    )
    private Set<CV> cvs;
}
