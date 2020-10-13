package com.thiago.digitalbank.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {


    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 4)
    private String agency;

    @Column(length = 8)
    private String account;

    private String bankCode = "001";

    @OneToOne

    private Client client;
}
