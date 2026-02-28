package org.example.accountms.controller;

import org.example.accountms.model.Account;
import org.example.accountms.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts/client-notifications")
public class ClientNotificationController {

    private static final Logger log = LoggerFactory.getLogger(ClientNotificationController.class);
    private final AccountRepository accountRepository;

    public ClientNotificationController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/created")
    public ResponseEntity<Void> handleClientCreated(@RequestBody Map<String, Object> payload) {
        Long clientId = Long.valueOf(payload.get("clientId").toString());
        log.info("Received client-created notification: clientId={}", clientId);

        Account account = new Account();
        account.setNumber("ACC-" + clientId + "-" + System.currentTimeMillis());
        account.setType("SAVINGS");
        account.setInitialAmount(0.0);
        account.setActive(true);
        account.setClientId(clientId);
        accountRepository.save(account);

        log.info("Default account created for clientId={}", clientId);
        return ResponseEntity.ok().build();
    }
}


