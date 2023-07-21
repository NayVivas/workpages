package com.hipergarzon.workpages.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ItemsCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String description;
    private boolean activeItems = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private ItemsCatalogueCategory category;

    public ItemsCatalogue() {
    }

    public ItemsCatalogue(String description, ItemsCatalogueCategory category, boolean activeItems) {
        this.description = description;
        this.category = category;
        this.activeItems = activeItems;
    }
}
