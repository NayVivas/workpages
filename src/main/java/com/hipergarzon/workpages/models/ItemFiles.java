package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class ItemFiles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String fileName;
    private String fileType;
    private String url;
    private Long size;
    private byte[] content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seeker_id")
    private Seeker seeker;

    public ItemFiles() {
    }

    public ItemFiles(String url, Seeker seeker) {
        this.url = url;
        this.seeker = seeker;
    }

    public ItemFiles(String fileName) {
        this.fileName = fileName;
    }
}
