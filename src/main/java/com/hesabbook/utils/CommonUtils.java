package com.hesabbook.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CommonUtils implements Serializable {
    public static final String PURCHASE_ORDER = "Purchase Order";
    public static final String PURCHASE_INVOICE = "Purchase Invoice";
    public static final String PURCHASE_RETURN = "Purchase Return";
    public static final String SALES_INVOICE = "Sales Invoice";
    public static final String SALES_RETURN = "Sales Return";
    public static final String PAYMENT_IN = "Payment In";
    public static final String PAYMENT_OUT = "Payment Out";
    public static final String CREDIT_NOTE = "Credit Note";
    public static final String DEBIT_NOTE = "Debit Note";
    public static final String ALL_TRANSACTION = "All Transaction";
    public static final String FULL_PAID = "Paid";
    public static final String UN_PAID = "Un Paid";
    public static final String PARTIAL_PAID = "Partial Paid";
}
