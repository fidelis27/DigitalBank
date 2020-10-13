package com.thiago.digitalbank.repository;

import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAllByName(String name, Pageable pageable);

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByCnh(String cnh);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByAddressId(Long id);

    Optional<Client> findByAddress(Address address);


}
