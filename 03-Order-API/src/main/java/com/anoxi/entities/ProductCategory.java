package com.anoxi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "product_category_tbl")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> products;
}

