package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemOthersData;
import com.hipergarzon.workpages.models.enums.*;
import lombok.Getter;

@Getter
public class ItemOthersDataDTO {
    private Long id;
    private String salary;
    private YesOrNo disability;
    private String typeDisability;
    private YesOrNo jobThisCompany;
    private String branchOffice;
    private String reasonForResignation; //
    private YesOrNo selectionStage; //
    private String stage;
    private String jobAspires;
    private String availability;
    private String typeJob;
    private YesOrNo rotary;
    private YesOrNo jobWeekend;
    private YesOrNo meetPeople;
    private String relationship;
    private String transport;

    public ItemOthersDataDTO() {
    }

    public ItemOthersDataDTO(ItemOthersData itemOthersData) {;
        this.id = itemOthersData.getId();
        this.salary = itemOthersData.getSalary();
        this.disability = itemOthersData.getDisability();
        this.typeDisability = itemOthersData.getTypeDisability();
        this.jobThisCompany = itemOthersData.getJobThisCompany();
        this.branchOffice = itemOthersData.getBranchOffice();
        this.reasonForResignation = itemOthersData.getReasonForResignation();
        this.selectionStage = itemOthersData.getSelectionStage();
        this.stage = itemOthersData.getStage();
        this.jobAspires = itemOthersData.getJobAspires();
        this.availability = itemOthersData.getAvailability();
        this.typeJob = itemOthersData.getTypeJob();
        this.rotary = itemOthersData.getRotary();
        this.jobWeekend = itemOthersData.getJobWeekend();
        this.meetPeople = itemOthersData.getMeetPeople();
        this.relationship = itemOthersData.getRelationship();
        this.transport = itemOthersData.getTransport();
    }


    public ItemOthersDataDTO(String salary, String branchOffice, YesOrNo rotary, YesOrNo jobWeekend, String transport) {
        this.salary = salary;
        this.branchOffice = branchOffice;
        this.rotary = rotary;
        this.jobWeekend = jobWeekend;
        this.transport = transport;
    }
}
