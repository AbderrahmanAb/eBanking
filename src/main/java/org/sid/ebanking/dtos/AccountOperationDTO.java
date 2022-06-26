package org.sid.ebanking.dtos;


import lombok.Data;
import org.sid.ebanking.enums.OperationType;
import java.util.Date;

@Data

public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private OperationType type;
    private double amount;
    private String description;
}
