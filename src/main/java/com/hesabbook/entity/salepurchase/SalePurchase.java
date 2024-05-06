package com.hesabbook.entity.salepurchase;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class SalePurchase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")


    private Integer id;//1
    private String markFullyPaid;
    private String businessName;//1
    private String phoneNumber;//1
    private String note;//1
    private String lastModifiedDate;//1
    private String userid;//1
    private String billType;//(Sale/Purcase....)//1
    private String billingAddress;//1
    private String shippingAddress;
    private String addAdditionalCharge;//1
    private String addDiscount;//1
    private String totalDiscount;//1
    private String totalTax;//1
    private String subtotalAmount;//1
    private String subtotalTax;
    private String subtotalDiscount;
    private String paidAmount;//1
    private String balanceAmount;//1
    private String paymentMode;

    private String partyName;
    private String partyMobile;

    private String transactionNumber;
    private String transactionDate;
    private String originalTransactionNumber;
    private String status;
    //SaleInvoice//1
    private String salesInvoiceNo;//1
    private String salesInvoiceDate;//1
    private String saleOriginalInvoiceNo;//1
    private String salesPaymentTerms;//1
    private String salesDueDate;
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

    private String salesPurchase;//1
    private String totalAmountCharge;
    @Transient
    private List<SalesPurchaseList> salesPurchaseLists;
    @Transient
    private String ledgerBalance;


    private String partyAmount;
    private String partyAmountType;//toCollect -  toPay +

    private String primary_user_id;
    private String secondary_user_id;

}
