package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.Admin;
import lombok.Getter;

@Getter
public class AdminDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String branchOffice;
    private Boolean activeAdmin;

    public AdminDTO() {
    }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
        this.branchOffice = admin.getBranchOffice();
        this.activeAdmin = admin.getActiveUser();
    }
}
