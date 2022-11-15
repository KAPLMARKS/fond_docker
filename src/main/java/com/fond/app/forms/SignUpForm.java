package com.fond.app.forms;

import com.fond.app.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {

    @Email(message = "{error.email.format}")
    private String email;

    @ValidPassword(message = "{error.password.format}")
    @NotEmpty(message = "{error.password.empty}")
    private String password;
}

