package com.hipergarzon.workpages.models;

import com.hipergarzon.workpages.models.enums.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Admin extends UserGeneral {
    private Long branchOfficeId;
    private boolean isEnabled;
    private String resetPasswordToken;
    private String jobInCompany;

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public Admin(String firstName, String lastName, String email, Rol rol, Boolean activeUser) {
        super(firstName, lastName, email, rol, activeUser);
    }
}
