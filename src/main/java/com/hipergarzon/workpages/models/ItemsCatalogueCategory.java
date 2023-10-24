package com.hipergarzon.workpages.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class ItemsCatalogueCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="category")
    private Set<ItemsCatalogue> itemsCatalogues;

    public ItemsCatalogueCategory() {
    }

    public ItemsCatalogueCategory(String name) {
        this.name = name;
    }
}
