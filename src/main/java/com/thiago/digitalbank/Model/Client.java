package com.thiago.digitalbank.Model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = " Name is required!")
    @Column(length = 20, nullable = false)
    private String nome;

    @CPF(message = " CPF is invalid")
    @Column(nullable = false)
    private String cpf;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cnh;

    private LocalDate dateBorn;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "document_id")
    private DocumentClient documentClient;



}
