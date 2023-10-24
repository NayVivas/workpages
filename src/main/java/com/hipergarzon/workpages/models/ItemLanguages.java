package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class ItemLanguages implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String languages;
    private String writeLevel;
    private String oralLevel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curriculum_id")
    private Seeker seeker;

    public ItemLanguages() {
    }

    public ItemLanguages(Seeker seeker, String languages, String writeLevel, String oralLevel) {
        this.languages = languages;
        this.writeLevel = writeLevel;
        this.oralLevel = oralLevel;
        this.seeker = seeker;
    }
}
