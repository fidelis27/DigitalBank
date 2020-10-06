package com.thiago.digitalbank.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;



@Entity
public class Client   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = " Name is required!")
    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    @NotEmpty(message = " O Sobrenome do Cliente é obrigatório")
    private String lastName;

    @CPF(message = " CPF is invalid")
    @Column(nullable = false)
    @CPF(message = " Por favor digite um CPF válido")
    private String cpf;

    @Email
    @NotEmpty(message = " O nome do Client é obrigatório")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = " A CNH do Cliente é obrigatória")
    @Size(max = 11, min = 11, message = "Digite um CNH válido")
    @Column(nullable = false)
    private String cnh;

    // @Past(message = "A data informada deve ser menor que a data atual")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateBorn;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "document_id")
    private DocumentClient documentClient;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(LocalDate dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DocumentClient getDocumentClient() {
        return documentClient;
    }

    public void setDocumentClient(DocumentClient documentClient) {
        this.documentClient = documentClient;
    }
}
