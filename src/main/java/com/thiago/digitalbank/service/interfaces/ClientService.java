package com.thiago.digitalbank.service.interfaces;

import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;


public interface ClientService {

    Client updateClientById(Long id, Client client);

    Client findClientById(Long id);

    Client findClientByCPF(String cpf);

    Client  findClientByCNH(String cnh);

    Client saveNewClient(Client client);

    Client responseIfExistsAddressToClientById(Long id);

    void deleteClientById(Long id);

    void saveAddressClient(Client client, Address address);
}
