package com.hesabbook.entity.party;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Data
@NoArgsConstructor
@Entity
@Table
@Builder
@Getter
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CACandidate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String licence;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Boolean enableSharing;
    private Boolean gstR1;  //Sales
    private Boolean gstR2;  //Purchase
    private String company;
    private String primary_user_id;
    private String secondary_user_id;
    private Date creationDateTime;
    @PrePersist
    public void prePersist() {
        this.creationDateTime = new Date();
    }

}
