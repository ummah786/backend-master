package com.hesabbook.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CommonUtils implements Serializable {
    public static final String PURCHASE_ORDER = "PURCHASE_ORDER";
    public static final String PURCHASE_INVOICE = "PURCHASE_INVOICE";
    public static final String PURCHASE_RETURN = "PURCHASE_RETURN";
    public static final String SALES_INVOICE = "SALE_INVOICE";
    public static final String SALES_RETURN = "SALE_RETURN";
    public static final String PAYMENT_IN = "PAYMENT_IN";
    public static final String PAYMENT_OUT = "PAYMENT_OUT";
    public static final String CREDIT_NOTE = "CREATE_NOTE";
    public static final String DEBIT_NOTE = "DEBIT_NOTE";
    public static final String ALL_TRANSACTION = "All Transaction";
    public static final String FULL_PAID = "Paid";
    public static final String UN_PAID = "Un Paid";
    public static final String PARTIAL_PAID = "Partial Paid";
    public static final String WITH_TAX="With Tax";
    public static final String WITH_OUT_TAX="Without Tax";


    //   salePurchaseObject["billType"] = "DELIVERY_CHALLAN";,QUOTATION,PROFORMA_INVOICE,PAYMENT_IN
    //  PAYMENT_OUT , DEBIT_NOTE PURCHASE_INVOICE PURCHASE_ORDER PURCHASE_RETURN CREATE_NOTE  DELIVERY_CHALLAN
}
