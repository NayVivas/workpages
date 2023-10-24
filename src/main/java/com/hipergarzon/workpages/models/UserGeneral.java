package com.hipergarzon.workpages.models;

import com.hipergarzon.workpages.models.enums.Rol;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class UserGeneral implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotBlank



    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern.List({
            @Pattern(regexp = ".*[a-z].*", message = "La contraseña debe tener al menos una letra minúscula"),
            @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe tener al menos una letra mayúscula"),
            @Pattern(regexp = ".*\\d.*", message = "La contraseña debe tener al menos un número"),
            @Pattern(regexp = ".*[@#$%^&+=].*", message = "La contraseña debe tener al menos un carácter especial")
    })
    private String password;
    private Rol rol;
    private Boolean activeUser = true;
    private String branchOffice;
    private Boolean formsComplete = false;
    private Boolean formsCompletePersonalData = false;
    private Boolean formsCompleteEducations = false;
    private Boolean formsCompleteJobExperince = false;
    private Boolean formsCompleteOthersData = false;

    public UserGeneral() {
    }

    public UserGeneral(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserGeneral(String firstName, String lastName, String email, Rol rol, Boolean activeUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rol = rol;
        this.activeUser = activeUser;
    }
}