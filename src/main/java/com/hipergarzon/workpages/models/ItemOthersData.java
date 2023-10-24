package com.hipergarzon.workpages.models;

import com.hipergarzon.workpages.models.enums.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class ItemOthersData implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String salary;
    private YesOrNo disability;
    private String typeDisability;
    private YesOrNo jobThisCompany;
    private String branchOffice;
    private String reasonForResignation;
    private String stage;
    private YesOrNo selectionStage;
    private String jobAspires;
    private String availability;
    private String typeJob;
    private YesOrNo rotary;
    private YesOrNo jobWeekend;
    private YesOrNo meetPeople;
    private String relationship;
    private String transport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curriculum_id")
    private Seeker seeker;

    public ItemOthersData() {
    }

    public ItemOthersData(Seeker seeker, String salary, YesOrNo disability, String typeDisability, YesOrNo jobThisCompany, String branchOffice, String reasonForResignation, YesOrNo selectionStage, String stage, String jobAspires, String availability, String typeJob, YesOrNo rotary, YesOrNo jobWeekend, YesOrNo meetPeople, String relationship, String transport) {
        this.salary = salary;
        this.disability = disability;
        this.typeDisability = typeDisability;
        this.jobThisCompany = jobThisCompany;
        this.branchOffice = branchOffice;
        this.reasonForResignation = reasonForResignation;
        this.stage = stage;
        this.selectionStage = selectionStage;
        this.jobAspires = jobAspires;
        this.availability = availability;
        this.typeJob = typeJob;
        this.rotary = rotary;
        this.jobWeekend = jobWeekend;
        this.meetPeople = meetPeople;
        this.relationship = relationship;
        this.transport = transport;
        this.seeker = seeker;
    }
}
