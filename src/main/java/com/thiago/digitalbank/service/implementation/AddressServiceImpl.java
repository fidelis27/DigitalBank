package com.thiago.digitalbank.service.implementation;

import com.thiago.digitalbank.Exception.SourceNotFound;
import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.repository.AddressRepository;
import com.thiago.digitalbank.service.interfaces.AddressService;
import com.thiago.digitalbank.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl  implements AddressService {
    @Autowired
    ClientService clientService;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address saveNewAddress(Long id, Address address) {
        Client clientUpdate = clientService.findClientById(id);


        System.out.println(clientUpdate);
        Address enderecoSalvo = addressRepository.save(address);

        clientService.saveAddressClient(clientUpdate, enderecoSalvo);
        return address;
    }

    @Override
    public Page<Address> findAllClients(Pageable pageable) {
        return null;
    }

    @Override
    public Address findAddressById(Long id) {
        return null;
    }

    @Override
    public Address findAddressCep(String cep) {
        return null;
    }

    @Override
    public Address updateAddressIfExists(Long id, Address address) {
        return null;
    }

    @Override
    public Address responseIfExistAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new SourceNotFound("Não foi encontrado endereco com esse id: " + id));
    }

    @Override
    public void deleteAddressById(Long id) {


    }
}
