package com.hesabbook.entity.expense;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
@Entity
@Table
public class Expense implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
    private String expenseId;
    private String expenseDate;
    private String lastModifiedDate;
    private String paymentMode;
    private String expenseType;
    private String note;
    private String totalAmount;
    private String expenseItemsList;
    private String primary_user_id;
    private String secondary_user_id;
    /*@OneToMany
    private List<ExpenseItems> expenseItemsList;*/
}
