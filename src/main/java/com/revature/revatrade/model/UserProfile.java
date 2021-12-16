package com.revature.revatrade.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.revatrade.shared.validation.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userProfileId;

//    @NotNull(message = "FirstName is required")
    private String firstName;
//    @NotNull(message = "LastName is required")
    private String lastName;
//    @NotNull(message = "Address is required")
    private String address;
//    @NotNull(message = "City is required")
    private String city;
//    @NotNull(message = "Zip-code is required")
//    @Size(min = 5 , message = "Zip Code must be min 5 and max 9 digits")
//    @Pattern(regexp = "^\\d{9}$", message = "Zip code must be numbers")
    private Integer zipCode;
    @Column
    private String profilePic;
    @Column
    private String aboutMe;
    @OneToOne( cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView(Views.Base.class)
    private User user;
}
