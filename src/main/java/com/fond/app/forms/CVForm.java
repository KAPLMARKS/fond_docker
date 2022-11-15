package com.fond.app.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVForm {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phone;
    @Size(min = 10,max = 12)
    private String inn;
    @NotNull
    @Size(min = 50,max = 500)
    private String aboutMe;
}
