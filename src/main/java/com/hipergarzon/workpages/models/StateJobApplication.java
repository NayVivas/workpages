package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class StateJobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    public StateJobApplication() {
    }
    public StateJobApplication(String name) {
        this.name = name;
    }
}
