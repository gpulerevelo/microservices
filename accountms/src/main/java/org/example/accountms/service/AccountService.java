package org.example.accountms.service;

import org.example.accountms.model.dto.AccountDto;
import org.example.accountms.model.dto.PartialAccountDto;

import java.util.List;

public interface AccountService {

    public List<AccountDto> getAll();
    public AccountDto getById(Long id);
    public AccountDto create(AccountDto accountDto);
    public AccountDto update(AccountDto accountDto);
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto);
    public void deleteById(Long id);
}
