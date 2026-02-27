package org.example.clientems.service;

import org.example.clientems.model.dto.ClientDto;
import org.example.clientems.model.dto.PartialClientDto;

import java.util.List;

public interface ClientService {
    public List<ClientDto> getAll();
    public ClientDto getById(Long id);
    public ClientDto create(ClientDto clientDto);
    public ClientDto update(ClientDto clientDto);
    public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto);
    public void deleteById(Long id);
}
