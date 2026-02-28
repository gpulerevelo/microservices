package org.example.accountms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankStatementDto {

    private Date date;
    private String client;
    private String accountNumber;
    private String accountType;
    private double initialAmount;
    private boolean isActive;
    private String transactionType;
    private double amount;
    private double balance;
}
