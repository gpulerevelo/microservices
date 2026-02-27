package org.example.accountms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends Base {
    private Date date;
    private String type;
    private double amount;
    private double balance;

    @Column(name = "account_id")
    private Long accountId;
}

