package com.oy.expensetrackerapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name")
    @NotBlank(message = "Expense name must not be null")
    @Size(min = 3, message = "Expense name should be at least 3 characters.")
    private String name;

    @Column(name = "expense_amount")
    @NotNull(message = "Amount should not be null")
    // TODO: VULNERABILITY-6 Business Logic Data Validation: Not checking negative amount
    private BigDecimal amount;

    private String description;

    @NotNull(message = "Category should not be null")
    private String category;

    private String billdata;
    private String billUrl;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date must not be null.")
    private Date date;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updateAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    // TODO: OnDelete -> Kullanici silinince expense kayitlari da siliniyor. Silinmis olan kullanicilara ait kayitlarin goruntulenebilmesi seklinde bir zafiyet olabilir.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
