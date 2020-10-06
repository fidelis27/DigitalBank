package com.thiago.digitalbank.repository;

import com.thiago.digitalbank.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCep(String cep);
}
