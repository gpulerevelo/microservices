package org.example.accountms.service;

import org.example.accountms.exception.ResourceNotFoundException;
import org.example.accountms.model.Account;
import org.example.accountms.model.dto.AccountDto;
import org.example.accountms.model.dto.PartialAccountDto;
import org.example.accountms.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }

    @Override
    public AccountDto create(AccountDto dto) {
        Account account = toEntity(dto);
        return toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto update(AccountDto dto) {
        if (dto.getId() == null || !accountRepository.existsById(dto.getId())) {
            throw new ResourceNotFoundException("Account not found with id: " + dto.getId());
        }
        return toDto(accountRepository.save(toEntity(dto)));
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partial) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        account.setActive(partial.isActive());
        return toDto(accountRepository.save(account));
    }

    @Override
    public void deleteById(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Account not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }

    private AccountDto toDto(Account a) {
        return new AccountDto(a.getId(), a.getNumber(), a.getType(), a.getInitialAmount(), a.isActive(), a.getClientId());
    }

    private Account toEntity(AccountDto d) {
        Account a = new Account();
        a.setId(d.getId());
        a.setNumber(d.getNumber());
        a.setType(d.getType());
        a.setInitialAmount(d.getInitialAmount());
        a.setActive(d.isActive());
        a.setClientId(d.getClientId());
        return a;
    }
}
