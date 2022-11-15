package com.fond.app.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMainDeclarationForm {
    private Long mainDeclaration;
    private String nameDeclaration;
}
