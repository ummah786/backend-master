package com.hesabbook.entity.account;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
@Entity
@Table
public class ManageUsers implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    private String accountBusinessName;
    private String name;
    private String mobileNumber;
    private String emailAddress;
    private String role;
    private String address;
    private String primary_user_id;
    private String secondary_user_id;
    private String tempPassword;

}

