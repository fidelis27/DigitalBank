package com.thiago.digitalbank.controllers;


import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.service.interfaces.AddressService;
import com.thiago.digitalbank.service.interfaces.ClientService;
import com.thiago.digitalbank.service.interfaces.DocumentService;
import com.thiago.digitalbank.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/signUp")
public class SignUpClient {

    @Autowired
    ClientService clientService;

    @Autowired
    AddressService addressService;

    @Autowired
    DocumentService documentService;


//    @GetMapping("/client")
//    public ResponseEntity<?> listAll(Pageable pageable) {
//        System.out.println(client.findAll());
//
//        return new ResponseEntity<>(clientRepository.findAll(pageable), HttpStatus.OK);
//
//    }

    @PostMapping("/client")
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> saveClient(@RequestBody @Valid Client client) {
        Client clientSalved = clientService.saveNewClient(client);
//        return new ResponseEntity<>(clientSalved.getId(), CREATED);
        URI location = geradorLocation(clientSalved.getId(), "/{id}/address");
        System.out.println(clientService.saveNewClient(client));
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(clientSalved);

    }
    @PostMapping("client/{id}/address")
    public ResponseEntity saveAddress(@RequestBody @Valid Address address, @PathVariable("id") Long id) {

        Address addressSalved = addressService.saveNewAddress(id, address);
        URI location = geradorLocation(id, "/file");

        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).body(addressSalved);
    }

    @PostMapping("client/{id}/address/file")
    public ResponseEntity saveDocument(@RequestParam("front") MultipartFile fotoDocumentoFrente,
                                         @RequestParam("back") MultipartFile fotoDocumentoVerso,
                                         @PathVariable("id") Long id) {
        String cwd = System.getProperty("user.dir");

        documentService.saveFilesDocClient(cwd, id, fotoDocumentoFrente, fotoDocumentoVerso);
        URI location = geradorLocation(id, "/agree");
        Client clientById = clientService.findClientById(id);

        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location))
                .body(clientById);

    }

    @PostMapping("client/{id}/address/file/agree")
    public ResponseEntity aceiteContrato(@PathVariable("id") Long id, @PathParam("agree") Boolean  agree) {
        Client client = clientService.responseIfExistsAddressToClientById(id);
        addressService.responseIfExistAddressById(id);
        System.out.println("passsou aqui" + agree);
        String message;
        if(agree) {
             message ="Que ótima notícia " + client.getName() + "!!! Iremos criar a sua conta";
        }else {
            message =  " Vamos dar um tempo para você pensar melhor";
        }
        return ResponseEntity.status(CREATED)
                .body(message);
    }

    private URI geradorLocation(Long id, String nextStep) {
       Client clientUpdate = clientService.findClientById(id);
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(nextStep)
                .buildAndExpand(clientUpdate.getId())
                .toUri();
    }


}
