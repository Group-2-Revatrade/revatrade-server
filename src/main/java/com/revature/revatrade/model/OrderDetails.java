package com.revature.revatrade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderDetailsId;
    @Column(nullable = false)
    private Double orderPrice;
    @Column(nullable = false) // i.e. product shampoo, bought two of them
    private Double orderQuantity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;
}
