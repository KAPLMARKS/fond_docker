package com.fond.app.dto;

import com.fond.app.models.DopDeclaration;
import com.fond.app.models.MainDeclaration;
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
public class MainDeclarationDto {
    private Long id;
    private String nameDeclaration;
    private Set<DopDeclaration> dopDeclarationSet;
    private MainDeclaration mainDeclaration;
    public static MainDeclarationDto from(MainDeclaration mainDeclaration){
        return MainDeclarationDto.builder()
                .id(mainDeclaration.getId())
                .nameDeclaration(mainDeclaration.getNameDeclaration())
                .dopDeclarationSet(mainDeclaration.getDopDeclarationSet())
                .mainDeclaration(mainDeclaration)
                .build();
    }

    public static List<MainDeclarationDto> from(List<MainDeclaration> mainDeclarations){
        return mainDeclarations.stream()
                .map(MainDeclarationDto::from)
                .collect(Collectors.toList());
    }
}
