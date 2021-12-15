package com.revature.revatrade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderId;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Integer zipCode;
    @Column(nullable = false)
    private Double orderAmount;
    @Column(nullable = false)
    private Calendar orderDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfile userProfile;
}
