package com.fond.app.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dop_declaration")
public class DopDeclaration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameDeclaration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maindeclaration_id")
    private MainDeclaration mainDeclaration;

    @PreRemove
    private void removeDopDeclarationFromMainDeclaration() {
        mainDeclaration.getDopDeclarationSet().remove(this);
    }
}
