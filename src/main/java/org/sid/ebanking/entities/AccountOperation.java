package org.sid.ebanking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebanking.enums.OperationType;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private double amount;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;

}
