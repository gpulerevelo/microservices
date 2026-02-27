package org.example.accountms.service;

import org.example.accountms.model.dto.AccountDto;
import org.example.accountms.model.dto.PartialAccountDto;
import org.example.accountms.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAll() {
        // Get all accounts
        return null;
    }

    @Override
    public AccountDto getById(Long id) {
        // Get accounts by id
        return null;
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        // Create account
        return null;
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        // Update account
        return null;
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) {
        // Partial update account
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // Delete account
    }

}

