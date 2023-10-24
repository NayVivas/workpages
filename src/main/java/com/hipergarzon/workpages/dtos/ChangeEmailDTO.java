package com.hipergarzon.workpages.dtos;

import lombok.Getter;

@Getter
public class ChangeEmailDTO {

    private String currentEmail;
    private String newEmail;
    private String confirmNewEmail;
}
