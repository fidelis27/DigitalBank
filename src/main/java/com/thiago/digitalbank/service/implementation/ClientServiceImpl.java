package com.thiago.digitalbank.service.implementation;

import com.thiago.digitalbank.Exception.SourceNotFound;
import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.repository.ClientRepository;
import com.thiago.digitalbank.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;



    @Override
    public Client findClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.orElseThrow(() -> new SourceNotFound("Não existe cliente cadastrado com o ID: " + id));
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
        return null;
    }

    @Override
    public Client findClientByCPF(String cpf) {
        return null;
    }

    @Override
    public Client findClientByCNH(String cnh) {
        return null;
    }

    @Override
    public Client responseIfExistsAddressToClientById(Long id) {

        Client clientFound =  findClientById(id);
        Optional<Client> address =clientRepository.findByAddress(clientFound.getAddress());//clienteRepository.findByEnderecoId(id);
        System.out.println(address);
        return address.orElseThrow(() -> new SourceNotFound("O cliente de ID: " + id + " não possui endereço cadastrado"));
    }

    @Override
    public void deleteClientById(Long id) {

    }
}
