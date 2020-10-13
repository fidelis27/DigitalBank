package com.thiago.digitalbank.service.implementation;

import com.thiago.digitalbank.Exception.EntityRequired;
import com.thiago.digitalbank.Exception.SourceEverExists;
import com.thiago.digitalbank.Exception.SourceNotFound;
import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.repository.AddressRepository;
import com.thiago.digitalbank.repository.ClientRepository;
import com.thiago.digitalbank.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;


    @Autowired
   AddressRepository addressRepository;

    @Override
    public Page<Client> findAllClients(Pageable pageable) {
        Page<Client> pageClients = clientRepository.findAll(pageable);
        return pageClients;
    }

    @Override
    public Page<Client> findClientByName(String name, Pageable pageable) {
        Page<Client> pageClients = clientRepository.findAllByName(name, pageable);
        return pageClients;
    }


    @Override
    public Client findClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.orElseThrow(() -> new EntityRequired("Not exists Client with id: " + id));
    }


    @Override
    public Client saveNewClient(Client client) {
        return clientRepository.save(client);
    }


    @Override
    public void saveAddressClient(Client client, Address address) {
        client.setAddress(address);
        clientRepository.save(client);

    }

    @Override
    public Client updateClientById(Long id, Client client) {

        Client clientFound = findClientById(id);
        clientFound.setCnh(client.getCnh());
        clientFound.setCpf(client.getCpf());
        clientFound.setDateBorn(client.getDateBorn());
        clientFound.setEmail(client.getEmail());
        clientFound.setName(client.getName());
        clientFound.setLastName(client.getLastName());
        return clientRepository.save(clientFound);

    }

    @Override
    public Client findClientByEmail(String email) {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        return clientOptional.orElseThrow(() -> new SourceNotFound("Não existe cliente cadastrado com o E-mail: " + email));
    }

    @Override
    public Client findClientByCPF(String cpf) {
        Optional<Client> client = clientRepository.findByCpf(cpf);
        return client.orElseThrow(() -> new SourceNotFound("O cliente com esse cpf: " + cpf + " não foi encontrado"));

    }
    @Override
    public void cpfEverExists(Client client) {
        Optional<Client> IfCpfExists = clientRepository.findByCpf(client.getCpf());
      if(IfCpfExists.isPresent()) {
          throw new SourceEverExists("CPF ever exists");
      }

    }

    @Override
    public void emailEverExists(Client client) {
        Optional<Client> IfEmailExists = clientRepository.findByEmail(client.getEmail());
        if(IfEmailExists.isPresent()) {
            throw new SourceEverExists("Email ever exists");
        }
    }

    @Override
    public Client findClientByCNH(String cnh) {
        Optional<Client> clientOptional = clientRepository.findByCnh(cnh);
        return clientOptional.orElseThrow(() -> new SourceNotFound("Não existe cliente cadastrado com o CNH: " + cnh));
    }

    @Override
    public Optional<Client> responseIfExistsAddressToClientById(Long id) {

        Client clientFound = findClientById(id);
        if(clientFound.getAddress() == null ) {
            throw  new EntityRequired("Client id: "+ id + " not has address");
        }

        Optional<Client> client = clientRepository.findByAddress(clientFound.getAddress());

            return client;


    }

    @Override
    public void deleteClientById(Long id) {
        Client clientFound = findClientById(id);
        clientRepository.delete(clientFound);

    }
}
