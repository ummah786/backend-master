package com.hesabbook.entity.expense;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
  /*  @PrePersist
    public void prePersist() {
        this.lastModifiedDate = new Date();
    }*/
    /*@OneToMany
    private List<ExpenseItems> expenseItemsList;*/
}
