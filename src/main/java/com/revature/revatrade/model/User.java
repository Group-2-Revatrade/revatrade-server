package com.revature.revatrade.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.revatrade.shared.validation.UniqueEmail;
import com.revature.revatrade.shared.validation.UniqueUsername;
import com.revature.revatrade.shared.validation.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String userType;
    @Column(nullable = true)
    private Integer profileId;
}