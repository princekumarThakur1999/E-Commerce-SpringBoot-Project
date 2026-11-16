package com.anoxi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    private String name;
    private String description;
    private String title;
    private BigDecimal unitPrice;
    private String imageUrl;
    private Boolean active;
    private Integer unitsStock;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id")  // column in `product_tbl` that holds the FK
    private ProductCategory productCategory;
}
