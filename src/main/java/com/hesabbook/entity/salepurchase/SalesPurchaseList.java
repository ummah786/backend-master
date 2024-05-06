package com.hesabbook.entity.salepurchase;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
public class SalesPurchaseList {
    private Integer sno;
    private String Item;
    private String itemCode;
    private String HSNNo;
    private String BatchNo;
    private Date MFGDate;
    private String Quantity;
    private String price;
    private String Discount;
    private String Tax;
    private String Amount;
    private String type;//salePurchase ....
    private String primary_user_id;
    private String secondary_user_id;
}
