package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.enums.YesOrNo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ItemPersonalDataDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String document;
    private String civilStatus;
    private YesOrNo children;
    private int amountChildren;
    private String ageChildren;
    private String phoneOne;
    private String phoneTwo;
    private String address;
    private String stateResidence;
    private String cityResidence;
    private String gender;

    public ItemPersonalDataDTO() {
    }

    public ItemPersonalDataDTO(ItemPersonalData itemPersonalData) {
        this.id = itemPersonalData.getId();
        this.firstName = itemPersonalData.getFirstName();
        this.lastName = itemPersonalData.getLastName();
        this.email = itemPersonalData.getEmail();
        this.birthday = itemPersonalData.getBirthday();
        this.document = itemPersonalData.getDocument();
        this.civilStatus = itemPersonalData.getCivilStatus();
        this.children = itemPersonalData.getChildren();
        this.amountChildren = itemPersonalData.getAmountChildren();
        this.ageChildren = itemPersonalData.getAgeChildren();
        this.phoneOne = itemPersonalData.getPhoneOne();
        this.phoneTwo = itemPersonalData.getPhoneTwo();
        this.address = itemPersonalData.getAddress();
        this.stateResidence = itemPersonalData.getStateResidence();
        this.cityResidence = itemPersonalData.getCityResidence();
        this.gender = itemPersonalData.getGender();
    }
    public ItemPersonalDataDTO(String firstName, String lastName, String email, String document, String civilStatus, YesOrNo children, String cityResidence, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.document = document;
        this.children = children;
        this.civilStatus = civilStatus;
        this.cityResidence = cityResidence;
        this.gender = gender;
    }
}
