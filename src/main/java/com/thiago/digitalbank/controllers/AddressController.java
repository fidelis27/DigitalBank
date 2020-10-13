package com.thiago.digitalbank.controllers;

import com.thiago.digitalbank.Model.Address;
import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.service.interfaces.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Endpoint control of address", description = "Control of clients address", tags = {"Control address"})
@RestController
@RequestMapping("v1/admin/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @ApiOperation(value = "find all address" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Address not found"),
            @ApiResponse(code = 403, message = "feature not allowed"),
            @ApiResponse(code = 401, message = "invalid credentials"),
            @ApiResponse(code = 200, message = "Address founds")})
    @GetMapping
    public Page<Address> findAllAddress(@PageableDefault(size = 5) Pageable pageable) {
        return addressService.findAllClients(pageable);
    }

    @ApiOperation(value = "find address by Id" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Address not found"),
            @ApiResponse(code = 403, message = "feature not allowed"),
            @ApiResponse(code = 401, message = "invalid credentials"),
            @ApiResponse(code = 200, message = "Address found")})
    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable Long id) {
       Address addressFound = addressService.findAddressById(id);
       return  ResponseEntity.ok(addressFound);
    }

    @ApiOperation(value = "find address by CEP" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "CEP not found"),
            @ApiResponse(code = 403, message = "feature not allowed"),
            @ApiResponse(code = 401, message = "invalid credentials"),
            @ApiResponse(code = 200, message = "CEP found")})
    @GetMapping("/cep/{cep}")
    public ResponseEntity<Address> findAddressByCep(@PathVariable String cep) {
        Address addressFound = addressService.findAddressCep(cep);
        return  ResponseEntity.ok(addressFound);
    }

    @ApiOperation(value = "Update Address" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "address not found"),
            @ApiResponse(code = 403, message = "feature not allowed"),
            @ApiResponse(code = 401, message = "invalid credentials"),
            @ApiResponse(code = 200, message = "address updated")})
    @PutMapping("/{id}")
    public Address updateClientById(@PathVariable Long id, @RequestBody @Valid Address address) {
        Address addressFound = addressService.updateAddressIfExists(id, address);
        return addressFound;
    }

    @ApiOperation(value = "Delete Address" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "address not found"),
            @ApiResponse(code = 403, message = "feature not allowed"),
            @ApiResponse(code = 401, message = "invalid credentials"),
            @ApiResponse(code = 200, message = "address deleted")})
    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
    }

}
