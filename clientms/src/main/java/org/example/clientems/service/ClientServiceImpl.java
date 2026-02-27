package org.example.clientems.service;

import org.example.clientems.exception.ResourceNotFoundException;
import org.example.clientems.model.Client;
import org.example.clientems.model.dto.ClientDto;
import org.example.clientems.model.dto.PartialClientDto;
import org.example.clientems.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getAll() {
        // Get all clients
        return clientRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getById(Long id) {
        // Get clients by id
        return clientRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        // Create client
        Client client = convertToEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return convertToDto(savedClient);
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        // Update client
        if (clientDto.getId() == null || !clientRepository.existsById(clientDto.getId())) {
            throw new ResourceNotFoundException("Client not found with id: " + clientDto.getId());
        }
        Client client = convertToEntity(clientDto);
        Client updatedClient = clientRepository.save(client);
        return convertToDto(updatedClient);
    }

    @Override
    public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto) {
        // Partial update account
        return clientRepository.findById(id)
                .map(client -> {
                    client.setActive(partialClientDto.isActive());
                    Client updatedClient = clientRepository.save(client);
                    return convertToDto(updatedClient);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        // Delete client
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    // Helper methods for DTO conversion
    private ClientDto convertToDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getDni(),
                client.getName(),
                client.getPassword(),
                client.getGender(),
                client.getAge(),
                client.getAddress(),
                client.getPhone(),
                client.isActive()
        );
    }

    private Client convertToEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setDni(clientDto.getDni());
        client.setName(clientDto.getName());
        client.setPassword(clientDto.getPassword());
        client.setGender(clientDto.getGender());
        client.setAge(clientDto.getAge());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setActive(clientDto.isActive());
        return client;
    }

}
