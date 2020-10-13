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

        Address addressSalved = addressRepository.save(address);

        clientService.saveAddressClient(clientUpdate, addressSalved);
        return address;
    }

    @Override
    public Page<Address> findAllClients(Pageable pageable) {
        Page<Address> pageAddress = addressRepository.findAll(pageable);
        return pageAddress;
    }

    @Override
    public Address findAddressById(Long id) {
        return responseIfExistAddressById(id);
    }

    @Override
    public Address findAddressCep(String cep) {
        return addressRepository.findByCep(cep)
                .orElseThrow(() -> new SourceNotFound("Not found address by cep: " + cep));
    }

    @Override
    public Address updateAddressIfExists(Long id, Address address) {

        Address addressFound = responseIfExistAddressById(id);
        addressFound.setCep(address.getCep());
        addressFound.setCity(address.getCity());
        addressFound.setComplement(address.getComplement());
        addressFound.setNeighborhood(address.getNeighborhood());
        addressFound.setState(address.getState());
        addressFound.setStreet(address.getStreet());
        addressRepository.save(addressFound);
        return addressFound;

    }

    @Override
    public Address responseIfExistAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new SourceNotFound("Not found address by id: " + id));
    }

    @Override
    public void deleteAddressById(Long id) {
        Address addressFound = responseIfExistAddressById(id);
        addressRepository.delete(addressFound);

    }
}
