package com.fond.app.dto;

import com.fond.app.models.*;
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
public class CVDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String inn;
    private String aboutMe;
    private Project project;

    public static CVDto from(CV cv){
        return CVDto.builder()
                .id(cv.getId())
                .name(cv.getName())
                .email(cv.getEmail())
                .phone(cv.getPhone())
                .inn(cv.getInn())
                .aboutMe(cv.getAboutMe())
                .project(cv.getProject())
                .build();
    }
    public static List<CVDto> from(List<CV> cvs) {
        return cvs.stream()
                .map(CVDto::from)
                .collect(Collectors.toList());
    }
}
