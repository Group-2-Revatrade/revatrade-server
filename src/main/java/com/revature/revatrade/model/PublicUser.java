package com.revature.revatrade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "public_users")
public class PublicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer publicUserId;
    /*
    * This datatype maybe a String type,
    * where we can store reference of a S3 bucket URL instead.
    * */
    @Column
    private Blob profilePic;
    @Column
    private String aboutMe;
    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile userProfile;
}
