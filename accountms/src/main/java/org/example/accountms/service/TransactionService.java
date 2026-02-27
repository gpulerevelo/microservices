package org.example.accountms.service;

import org.example.accountms.model.dto.BankStatementDto;
import org.example.accountms.model.dto.TransactionDto;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    public List<TransactionDto> getAll();
    public TransactionDto getById(Long id);
    public TransactionDto create(TransactionDto transactionDto);
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, @Param("dateTransactionStart") Date dateTransactionStart, @Param("dateTransactionEnd") Date dateTransactionEnd);
    public TransactionDto getLastByAccountId(Long accountId);
}
