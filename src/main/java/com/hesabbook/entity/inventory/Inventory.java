package com.hesabbook.entity.inventory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String category; // create a new
    private String companyName; // create a new
    private String itemCode;
    private String barCodeValue; // create a new
    private String itemDescription;
    private String totalStock;
    private String lowStock;
    private String lowStockCheckBox;
    private String rackNo; // create a new
    private String challanNo;
    private String unitNo; // dose, strip, injection, tablets
    private String packageItems; // 5 * 10
    private String salePrice;
    private String actualSalePrice;
    private String salePriceTax;
    private String purchasePrice;
    private String actualPurchasePrice;
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
    private String supplier;
    private String warehouse;
    private String hsn;
    private Date creationDateTime;
    private String primary_user_id;
    private String secondary_user_id;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PartyWiseReport> partyWiseReportList;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockDetails> stockDetailsList;


    @Transient
    private String snoTable;
    @Transient
    private String discountTable;
    @Transient
    private String taxTable;
    @Transient
    private String amountTable;

    // New Fields
    @Transient
    private Integer quantity;
    @Transient
    private Double discount;
    @Transient
    private Double total;

    @PrePersist
    public void prePersist() {
        this.creationDateTime = new Date();
    }
}
