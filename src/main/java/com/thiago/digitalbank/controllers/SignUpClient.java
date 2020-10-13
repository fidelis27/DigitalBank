package com.thiago.digitalbank.controllers;


import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.repository.AddressRepository;
import com.thiago.digitalbank.repository.ClientRepository;
import com.thiago.digitalbank.service.interfaces.AddressService;
import com.thiago.digitalbank.service.interfaces.ClientService;
import com.thiago.digitalbank.service.interfaces.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Api(value = "Endpoint Sign up clients", description = "Sign Up clients  step : Client > Address> Documents > Agree", tags = {"Sign Up"})
@RestController
@RequestMapping("v1/signUp")
public class SignUpClient {

    @Autowired
    ClientService clientService;

    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DocumentService documentService;

    @Autowired
    ClientRepository clientRepository;


    @PostMapping("/client")
    @ApiOperation(value = "Save clients")
    @ApiResponses(value = {@ApiResponse(code = 409, message = "CPF, EMAIL ever exists"),
            @ApiResponse(code = 201, message = "created client with success")})
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> saveClient(@RequestBody @Valid Client client) {
//
        clientService.cpfEverExists(client);
        clientService.emailEverExists(client);
        Client clientSalved = clientService.saveNewClient(client);

        URI location = addLocation(clientSalved.getId(), "/{id}/address");
        System.out.println(clientService.saveNewClient(client));
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(clientSalved);

    }
    @ApiOperation(value = "Save address client")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Could not create Address"),
            @ApiResponse(code = 422, message = "Client Registration Required"),
            @ApiResponse(code = 201, message = "Address created successfully")})
    @PostMapping("client/{id}/address")
    public ResponseEntity saveAddress(@RequestBody @Valid Address address, @PathVariable("id") Long id) {

        Address addressSalved = addressService.saveNewAddress(id, address);
        URI location = addLocation(id, "/file");
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).body(addressSalved);

    }

    @ApiOperation(value = "Save documents of the client front and back")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Document could not be saved"),
            @ApiResponse(code = 422, message = "Client Registration and Address Required"),
            @ApiResponse(code = 201, message = "Document saved successfully")})
    @PostMapping("client/{id}/address/file")
    public ResponseEntity saveDocument(@RequestParam("front") MultipartFile FileFront,
                                       @RequestParam("back") MultipartFile FileBack,
                                       @PathVariable("id") Long id) {
        String cwd = System.getProperty("user.dir");
        Optional<Client> address= clientService.responseIfExistsAddressToClientById(id);
        Client client = clientService.findClientById(id);
        documentService.saveFilesDocClient(cwd, id, FileFront, FileBack);
        URI location = addLocation(id, "/agree");


        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(client);

    }
    @ApiOperation(value = "agree to terms of account opening")
    @ApiResponses (value = {@ApiResponse (code = 404, message = "The document could not be created"),
            @ApiResponse (code = 422, message = "Client Registration, Address and Identification Document Required"),
            @ApiResponse (code = 200, message = "Accept received successfully")})
    @PostMapping("client/{id}/address/file/agree")
    public ResponseEntity acceptContract(@PathVariable("id") Long id, @PathParam("agree") Boolean agree) {
        Optional<Client> address= clientService.responseIfExistsAddressToClientById(id);
        Client client = clientService.findClientById(id);

        String message;
        if (agree) {
            message = "What a great news" + client.getName () + "!!! We will create your account";
        } else {
            message = "We will give you time to think better";
        }
        return ResponseEntity.status(CREATED)
                .body(message);
    }

    private URI addLocation(Long id, String nextStep) {
        Client clientUpdate = clientService.findClientById(id);
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(nextStep)
                .buildAndExpand(clientUpdate.getId())
                .toUri();
    }


}
