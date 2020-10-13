package com.thiago.digitalbank.controllers;

import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.service.interfaces.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Customer control endpoint", description = "Internal control of customer data", tags = {"Customer control"})
@RestController
@RequestMapping("v1/admin/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @ApiOperation(value = "Search all customers registered in the database")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Customers not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Localized customers")})
    @GetMapping
    public Page<Client> findAllClients(@PageableDefault(size = 5) Pageable pageable) {
        Page<Client> allClients = clientService.findAllClients(pageable);
        return  allClients;
    }



    @ApiOperation (value = "Update the Client if exists specified by the given Id")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "Customer not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Customer updated successfully")})
    @PutMapping("/{id}")
    public Client updateClientById(@PathVariable Long id, @RequestBody @Valid Client client) {
        Client clientFound = clientService.updateClientById(id, client);
        return clientFound;
    }

    @ApiOperation (value = "Search for the Client specified by the given Id")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "Customer not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Customer located")})

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientsById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }



    @ApiOperation (value = "Search for the Client specified by the Name provided")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "Customers not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Client found")})
    @GetMapping("/name/{name}")
    public ResponseEntity<Page<Client>> findClientsByName(@PathVariable String name, @PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(clientService.findClientByName(name, pageable), HttpStatus.OK);
    }


    @ApiOperation (value = "Search for the Client specified by the informed CPF")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "Customer not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Client found")})
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Client> findClientByCpf(@PathVariable String cpf) {
        System.out.println(cpf);
        return new ResponseEntity<>(clientService.findClientByCPF(cpf), HttpStatus.OK);

    }



    @ApiOperation(value = "Search for the Customer specified by the informed CNH")
    @ApiResponses(value = {@ApiResponse (code = 404, message = "Customer not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Client found")})
    @GetMapping("/cnh/{cnh}")
    public ResponseEntity<Client> findClientByCnh(@PathVariable String cnh) {

        return new ResponseEntity<>(clientService.findClientByCNH(cnh), HttpStatus.OK);

    }


    @ApiOperation (value = "Search for the specified Client by the informed Email")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "Customer not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials"),
            @ApiResponse (code = 200, message = "Client found")})
    @GetMapping("/email/{email}")
      public ResponseEntity<Client> findClientByEmail(@PathVariable String email) { return new ResponseEntity<>(clientService.findClientByEmail(email), HttpStatus.OK);

    }

    @ApiOperation (value = "Delete the Client, if any, specified by the informed Id")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "User not found"),
            @ApiResponse (code = 403, message = "You do not have permission to view this resource"),
            @ApiResponse (code = 401, message = "You don't have valid authentication credentials")})
    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }



}
