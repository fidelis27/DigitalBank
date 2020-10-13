package com.thiago.digitalbank.service.interfaces;

import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ClientService {
    Page<Client> findAllClients(Pageable pageable);

    Page<Client> findClientByName(String name, Pageable pageable);

    Client updateClientById(Long id, Client client);

    Client findClientById(Long id);

    Client findClientByEmail(String email);

    Client findClientByCPF(String cpf);

    Client  findClientByCNH(String cnh);

    Client saveNewClient(Client client);

    Optional<Client> responseIfExistsAddressToClientById(Long id);

    void deleteClientById(Long id);

    void saveAddressClient(Client client, Address address);

    void cpfEverExists(Client client) ;

    void emailEverExists(Client client) ;
}
