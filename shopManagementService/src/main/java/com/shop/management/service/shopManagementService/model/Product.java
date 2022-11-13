package com.shop.management.service.shopManagementService.model;//package com.shop.management.service.shopManagementService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private double price;
    private String description;


    @ManyToMany(cascade = { CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.DETACH,
                            CascadeType.REFRESH
    })
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category){
        categories.add(category);
    }

    public void removeCategory(Category category){
        if(!categories.isEmpty()){
            categories.remove(category);
        }
    }

}