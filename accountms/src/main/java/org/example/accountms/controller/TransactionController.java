package org.example.accountms.controller;

import org.example.accountms.model.dto.BankStatementDto;
import org.example.accountms.model.dto.TransactionDto;
import org.example.accountms.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public ResponseEntity<List<TransactionDto>> getAll(){
        // api/transactions
        // Get all transactions
        return null;
    }

    public ResponseEntity<TransactionDto> get(@PathVariable Long id){
        // api/transactions/{id}
        // Get transactions by id
        return null;
    }

    public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transactionDto){
        // api/transactions
        // Create transactions
        return null;
    }

    public ResponseEntity<List<BankStatementDto>> report(@PathVariable Long clientId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTransactionStart, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTransactionEnd) {
        // api/transactions/clients/{clientId}/report
        // Get report
        return null;
    }
}
