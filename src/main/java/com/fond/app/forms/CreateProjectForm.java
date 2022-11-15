package com.fond.app.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProjectForm {
    @NotNull
    private String nameProject;
    @NotNull
    private String description;
    @NotNull
    @Size(min = 10,max = 12)
    private String inn;
    @NotNull
    private Long mainDeclaration;
    @NotNull
    private Long dopDeclaration;
    private String keywords;
}
