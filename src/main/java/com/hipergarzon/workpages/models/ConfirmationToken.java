package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = UserGeneral.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserGeneral userGeneral;

    public ConfirmationToken() {
    }

    public ConfirmationToken(UserGeneral userGeneral) {
        this.userGeneral = userGeneral;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
