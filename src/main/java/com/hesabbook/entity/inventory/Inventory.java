package com.hesabbook.entity.inventory;

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
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    private String userName;
    private String item;
    private String category;//create a new
    private String companyName;//create a new
    private String itemCode;
    private String barCodeValue; //create a new
    private String itemDescription;
    private String totalStock;
    private String lowStock;
    private String lowStockCheckBox;
    private String rackNo;//create a new
    private String challanNo;
    private String unitNo;//dose,strip,injection,tables
    private String packageItems; // 5  * 10
    private String salePrice;
    private String salePriceTax;
    private String purchasePrice;
    private String purchasePriceTax;
    private String salt;
    private String batchNo;
    private String mfgDate;
    private String expireDate;
    private String mrp;
    private String compensationCess;
    private String gst;
    private String utgst;
    private String sgst;
    private String igst;
    private String cgst;
    private String gstPercentage;
    private String supplier;
    private String warehouse;
    private String hsn;
    private Date creationDateTime;
    private String primary_user_id;
    private String secondary_user_id;
    @Transient
    private String snoTable;
    @Transient
    private String discountTable;
    @Transient
    private String taxTable;
    @Transient
    private String amountTable;
    @PrePersist
    public void prePersist() {
        this.creationDateTime = new Date();
    }
}
