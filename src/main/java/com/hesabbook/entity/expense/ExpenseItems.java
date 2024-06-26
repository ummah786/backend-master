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
}
