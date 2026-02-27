package org.example.clientems.controller;

import org.example.clientems.model.dto.ClientDto;
import org.example.clientems.model.dto.PartialClientDto;
import org.example.clientems.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        // api/clients
        // Get all clients
        List<ClientDto> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> get(@PathVariable Long id) {
        // api/clients/{id}
        // Get clients by id
        ClientDto clientDto = clientService.getById(id);
        return ResponseEntity.ok(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
        // api/clients
        // Create client
        ClientDto createdClient = clientService.create(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        // api/clients/{id}
        // Update client
        clientDto.setId(id);
        ClientDto updatedClient = clientService.update(clientDto);
        return ResponseEntity.ok(updatedClient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> partialUpdate(@PathVariable Long id, @RequestBody PartialClientDto partialClientDto) {
        // api/clients/{id}
        // Partial update client
        ClientDto updatedClient = clientService.partialUpdate(id, partialClientDto);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // api/clients/{id}
        // Delete client
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
