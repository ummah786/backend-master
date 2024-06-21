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
public class AccountDetails implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;

    private String businessName;
    private String phoneNumber;
    private String email;
    private String billingAddress;
    private String city;
    private String district;
    private String state;
    private String pinCode;
    private String gstNumber;
    private String gstUser;//are you gst registered;
    private String panNumber;
    private String businessType;
    private String industryType;
    private String businessRegistrationType;
    private String logo;
    private String signature;

    private String  billingRequirement;
    private String businessSize;;
    private String  foundBy;
    private String  language;

    private String primary_user_id;
    private String secondary_user_id;
}
