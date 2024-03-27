package com.hesabbook.entity.inventory;

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
public class Godowon implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    private String wareHouseName;
    private String address;
    private String state;
    private String zipCode;
    private String notes;
    private String date;
    private String city;
    private String primary_user_id;
    private String secondary_user_id;
}
