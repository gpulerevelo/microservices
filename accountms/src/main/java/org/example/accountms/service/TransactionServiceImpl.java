package org.example.accountms.service;

import org.example.accountms.exception.InsufficientBalanceException;
import org.example.accountms.exception.ResourceNotFoundException;
import org.example.accountms.model.Account;
import org.example.accountms.model.Transaction;
import org.example.accountms.model.dto.BankStatementDto;
import org.example.accountms.model.dto.TransactionDto;
import org.example.accountms.repository.AccountRepository;
import org.example.accountms.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<TransactionDto> getAll() {
        return transactionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(Long id) {
        return transactionRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    public TransactionDto create(TransactionDto dto) {
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + dto.getAccountId()));

        // Calculate new balance: get last balance or use initialAmount
        double currentBalance = getLastByAccountId(dto.getAccountId()) != null
                ? getLastByAccountId(dto.getAccountId()).getBalance()
                : account.getInitialAmount();

        double newBalance = currentBalance + dto.getAmount(); // positive = deposit, negative = withdrawal
        if (newBalance < 0) {
            throw new InsufficientBalanceException();
        }

        Transaction tx = new Transaction();
        tx.setDate(new Date());
        tx.setType(dto.getType());
        tx.setAmount(dto.getAmount());
        tx.setBalance(newBalance);
        tx.setAccountId(dto.getAccountId());
        return toDto(transactionRepository.save(tx));
    }

    @Override
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date start, Date end) {
        List<Account> accounts = accountRepository.findByClientId(clientId);
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("No accounts found for clientId: " + clientId);
        }

        List<Long> accountIds = accounts.stream().map(Account::getId).collect(Collectors.toList());
        List<Transaction> transactions = transactionRepository.findByAccountIdInAndDateBetween(accountIds, start, end);

        return transactions.stream().map(tx -> {
            Account acc = accounts.stream()
                    .filter(a -> a.getId().equals(tx.getAccountId()))
                    .findFirst().orElse(new Account());
            return new BankStatementDto(
                    tx.getDate(),
                    "Client " + clientId,
                    acc.getNumber(),
                    acc.getType(),
                    acc.getInitialAmount(),
                    acc.isActive(),
                    tx.getType(),
                    tx.getAmount(),
                    tx.getBalance()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public TransactionDto getLastByAccountId(Long accountId) {
        List<Transaction> txs = transactionRepository.findByAccountIdOrderByDateDesc(accountId);
        return txs.isEmpty() ? null : toDto(txs.get(0));
    }

    private TransactionDto toDto(Transaction t) {
        return new TransactionDto(t.getId(), t.getDate(), t.getType(), t.getAmount(), t.getBalance(), t.getAccountId());
    }
}
