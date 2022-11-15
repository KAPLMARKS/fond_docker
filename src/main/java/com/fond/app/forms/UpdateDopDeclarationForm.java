package com.fond.app.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDopDeclarationForm {
    private Long mainDeclaration;
    private Long dopDeclaration;
    private String nameDeclaration;
}
