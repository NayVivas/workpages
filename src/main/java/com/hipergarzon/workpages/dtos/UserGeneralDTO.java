package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.models.enums.Rol;
import lombok.Getter;

@Getter
public class UserGeneralDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Rol rol;
    private Boolean activeUser = false;
    private String branchOffice;
    private Boolean formsComplete = false;
    private Boolean formsCompletePersonalData = false;
    private Boolean formsCompleteEducations = false;
    private Boolean formsCompleteJobExperince = false;
    private Boolean formsCompleteOthersData = false;

    public UserGeneralDTO() {
    }
    public UserGeneralDTO(UserGeneral userGeneral) {
        this.id = userGeneral.getId();
        this.firstName = userGeneral.getFirstName();
        this.lastName = userGeneral.getLastName();
        this.email = userGeneral.getEmail();
        this.rol = userGeneral.getRol();
        this.activeUser = userGeneral.getActiveUser();
        this.branchOffice = userGeneral.getBranchOffice();
        this.formsComplete = userGeneral.getFormsComplete();
        this.formsCompletePersonalData = userGeneral.getFormsCompletePersonalData();
        this.formsCompleteEducations = userGeneral.getFormsCompleteEducations();
        this.formsCompleteJobExperince = userGeneral.getFormsCompleteJobExperince();
        this.formsCompleteOthersData = userGeneral.getFormsCompleteOthersData();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
