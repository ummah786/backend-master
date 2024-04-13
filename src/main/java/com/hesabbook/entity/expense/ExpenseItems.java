package com.hesabbook.entity.expense;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
public class ExpenseItems {
    private Integer id;
    private String itemName;
    private String quantity;
    private String rate;
    private Double total;
    private String primary_user_id;
    private String secondary_user_id;
}
