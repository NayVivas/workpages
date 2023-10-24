package com.hipergarzon.workpages.models;

import com.hipergarzon.workpages.models.enums.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recruiter extends UserGeneral {
    private Long branchOfficeId;
    /*private boolean isEnabled;
    private String resetPasswordToken;*/
    private String jobInCompany;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="recruiter")
    private Set<JobVacancy> jobVacancies;

    public Recruiter() {
    }

    public Recruiter(String firstName, String lastName, String email, Rol rol, Boolean activeUser) {
        super(firstName, lastName, email, rol, activeUser);
    }

    public Recruiter(String luana, String saldana, String s, Rol recruiter, String acarigua) {
    }

}
