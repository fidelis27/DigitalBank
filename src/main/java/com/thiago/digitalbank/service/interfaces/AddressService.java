package com.thiago.digitalbank.service.interfaces;

import com.thiago.digitalbank.Model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    Address saveNewAddress(Long id, Address address);

    Page<Address> findAllClients(Pageable pageable);

    Address findAddressById(Long id);

    Address findAddressCep(String cep);

    Address updateAddressIfExists(Long id, Address address);

    Address responseIfExistAddressById(Long id);

    void deleteAddressById(Long id);
}
