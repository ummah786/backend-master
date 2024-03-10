package com.hesabbook.entity.party;

import java.io.Serializable;
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
public class Partner implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    private String pName;//show
    private String mobileNumber;//show
    private String email;
    private String openingBalance;  //show
    private String openingBalanceType;
    private String partyType;  //customer ya Supllier  //show
    private String partyCategory;
    private String billingAddress;
    private String shippingAddress;
    private String gstNumber;
    private String creditPeriod;
    private String creditLimit;  //show
    private String creditPeriodType;
    private String loyality;
    private String company;
    private String primary_user_id;
    private String secondary_user_id;
    private Date creationDateTime;

    @PrePersist
    public void prePersist() {
        this.creationDateTime = new Date();
    }
}