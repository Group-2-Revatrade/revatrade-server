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
    @NotNull(message = "Username is required")
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;
    @NotNull(message = "Email is required")
    @Email
    @UniqueEmail
    @JsonView(Views.Base.class)
    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 5, message = "Password must be minimum 5 characters long")
    private String password;
    //    @NotNull
    @Column(nullable = false)
    @JsonView(Views.Base.class)
    private String userType;
    @Column(nullable = true)
    private Integer profileId;


}