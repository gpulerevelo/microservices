package org.example.accountms.service;

import org.example.accountms.model.dto.BankStatementDto;
import org.example.accountms.model.dto.TransactionDto;
import org.example.accountms.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDto> getAll() {
        // Get all transactions
        return null;
    }

    @Override
    public TransactionDto getById(Long id) {
        // Get transactions by id
        return null;
    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        // Create transaction
        return null;
    }

    @Override
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
                                                                        Date dateTransactionEnd) {
        // Report
        return null;
    }

    @Override
    public TransactionDto getLastByAccountId(Long accountId) {
        // If you need it
        return null;
    }

}
