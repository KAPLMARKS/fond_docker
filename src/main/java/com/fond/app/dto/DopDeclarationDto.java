package com.fond.app.dto;

import com.fond.app.models.DopDeclaration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DopDeclarationDto {
    private Long id;
    private String nameDeclaration;
    private MainDeclarationDto mainDeclarationDto;

    public static DopDeclarationDto from(DopDeclaration dopDeclaration){
        return DopDeclarationDto.builder()
                .id(dopDeclaration.getId())
                .nameDeclaration(dopDeclaration.getNameDeclaration())
                .mainDeclarationDto(MainDeclarationDto.from(dopDeclaration.getMainDeclaration()))
                .build();
    }
    public static List<DopDeclarationDto> from(List<DopDeclaration> dopDeclarations){
        return dopDeclarations.stream()
                .map(DopDeclarationDto::from)
                .collect(Collectors.toList());
    }
}
