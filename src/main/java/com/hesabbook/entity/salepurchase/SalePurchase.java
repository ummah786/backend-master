package com.hesabbook.entity.salepurchase;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hesabbook.entity.AddAdditionalCharge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "sale_purchase")
public class SalePurchase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;//1
    //logo
    @Lob
    private byte[] logoImage;
    @Lob
    private byte[] signatureImage;
    //Party Details
    private String partyId;
    private String partyName;
    private String partyPhone;
    private String partyBillingAddress;
    private String partyShippingAddress;
    private String partyGst;
    //type of bill
    private String billType;  //saleInvoice  //salePurchase...
    //condition
    private String addNote;
    private String addTermsAndCondition;
    // details of bills
    private String addAdditionalCharges;
    private String addDiscount;
    private String autoRoundOffValue;
    private String autoRoundOffMark;
    private String markFullyPaid;
    private String amountReceived;
    private String balanceAmount;
    private String totalTaxableAmount;   //without tax value
    private String totalGstRats;    //SGST@2.5 CGST@2.5  SGST@6  CGST@6  SGST@14 CGST@14
    //table total Values details
    private String totalTableDiscount;
    private String totalTableTax;
    private String totalTableAmount;
    //items
    @Column(name = "items", columnDefinition = "JSON")
    private String items;



    private String transactionNumber;
    private String transactionDate;
    private String originalTransactionNumber;
    private String status;
    //SaleInvoice//1
    private String salesInvoiceNo;//1
    // Date
    private LocalDateTime salesInvoiceDate;//1
    private String saleOriginalInvoiceNo;//1
    private String salesPaymentTerms;//1
    private LocalDateTime salesDueDate;
    //purchase invoice//1
    private String purchaseInvNo;//1
    private String purchaseInvDate;//1
    private String purchaseOriginalInvNo;//1
    private String PaymentTerms;//1
    private String purchaseDueDate;
    //purchase order//1
    private String purchaseNo;//1
    private String purchaseDate;//1
    private String validDate;
    //purchase Return//1
    private String purchaseReturnNo;//1
    private String purchaseReturnDate;

    //Sales Return//1
    private String salesReturnNo;//1
    private String salesReturnDate;//1

    private String creditNoteNo;
    private String creditNoteDate;

    private String debitNoteNo;
    private String debitNoteDate;
    //Payment IN/OUT
    private String amount;
    private String paymentDate;
    private String paymentNumber;
    private String paymentType;//IN OUT
    private String paymentNote;
    private String gson;
    @Transient
    private List<SalesPurchaseList> salesPurchaseLists;
    @Transient
    private String ledgerBalance;


    private String partyAmount;
    private String partyAmountType;//toCollect -  toPay +

    private String primary_user_id;
    private String secondary_user_id;
    private Date creationDateTime;
    @PrePersist
    public void prePersist() {
        this.creationDateTime = new Date();
    }

}
