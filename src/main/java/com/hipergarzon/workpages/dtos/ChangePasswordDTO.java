package com.hipergarzon.workpages.dtos;

import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class ChangePasswordDTO {

    private String currentPassword;

    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern.List({
            @Pattern(regexp = ".*[a-z].*", message = "La contraseña debe tener al menos una letra minúscula"),
            @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe tener al menos una letra mayúscula"),
            @Pattern(regexp = ".*\\d.*", message = "La contraseña debe tener al menos un número"),
            @Pattern(regexp = ".*[@#$%^&+=].*", message = "La contraseña debe tener al menos un carácter especial")
    })
    private String newPassword;
    private String confirmNewPassword;
}
