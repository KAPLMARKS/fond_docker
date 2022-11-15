package com.fond.app.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDopDeclarationForm {
    private Long mainDeclaration;
    private String dopDeclaration;
}
