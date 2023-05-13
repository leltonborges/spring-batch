package com.example.demo.service.project;

import com.example.demo.model.Client;
import com.example.demo.repository.project.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> queryPage(Pageable pageable){
        return this.clientRepository.findAll(pageable);
    }
}
