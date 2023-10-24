package com.hipergarzon.workpages.models;

import com.hipergarzon.workpages.models.enums.YesOrNo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class ItemPersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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
    private String cityResidence;
    private String stateResidence;
    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seeker_id")
    private Seeker seeker;

    public ItemPersonalData() {
    }

    public ItemPersonalData(Seeker seeker, String firstName, String lastName, String email, LocalDate birthday, String document, String civilStatus, YesOrNo children, int amountChildren, String ageChildren, String phoneOne, String phoneTwo, String address, String cityResidence, String stateResidence, String gender) {
        this.seeker = seeker;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.document = document;
        this.civilStatus = civilStatus;
        this.children = children;
        this.amountChildren = amountChildren;
        this.ageChildren = ageChildren;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.address = address;
        this.cityResidence = cityResidence;
        this.stateResidence = stateResidence;
        this.gender = gender;
    }

    public ItemPersonalData(Seeker seeker, String firstName, String lastName, String email, LocalDate birthday, String document, String civilStatus, YesOrNo children, int amountChildren, String ageChildren, String phoneOne, String phoneTwo, String address, String cityResidence, String stateResidence, String gender, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.document = document;
        this.civilStatus = civilStatus;
        this.children = children;
        this.amountChildren = amountChildren;
        this.ageChildren = ageChildren;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.address = address;
        this.cityResidence = cityResidence;
        this.stateResidence = stateResidence;
        this.gender = gender;
        this.seeker = seeker;
    }
}
