package org.example.accountms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String number;
    private String type;
    private double initialAmount;
    private boolean isActive;
    private Long clientId;
}
