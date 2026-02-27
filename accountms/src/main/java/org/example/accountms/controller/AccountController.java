package org.example.accountms.controller;

import org.example.accountms.model.dto.AccountDto;
import org.example.accountms.model.dto.PartialAccountDto;
import org.example.accountms.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public ResponseEntity<List<AccountDto>> getAll(){
        // api/accounts
        // Get all accounts
        return null;
    }

    public ResponseEntity<AccountDto> get(@PathVariable Long id){
        // api/accounts/{id}
        // Get accounts by id
        return null;
    }

    public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto){
        // api/accounts
        // Create accounts
        return null;
    }

    public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto){
        // api/accounts/{id}
        // Update accounts
        return null;
    }

    public ResponseEntity<AccountDto> partialUpdate(@PathVariable Long id, @RequestBody PartialAccountDto partialAccountDto){
        // api/accounts/{id}
        // Partial update accounts
        return null;
    }

    public ResponseEntity<Void> delete(@PathVariable Long id){
        // api/accounts/{id}
        // Delete accounts
        return null;
    }
}

