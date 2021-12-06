package com.revature.revatrade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer productId;
    @Column(nullable = false, unique = true)
    private String productName;
    @Column(nullable = false)
    private Double productPrice;
    @Column(nullable = false)
    private Integer productQuantity;
    @Column
    private Boolean discount;
    @Column
    private Double discountRate;
    @Column
    private String description;
    @Column
    private String productPic;
    /*@Column // For next iteration, look up table recommended
    private String category;*/
}
