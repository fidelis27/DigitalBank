package com.thiago.digitalbank.Mapper;

import ch.qos.logback.core.net.server.Client;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")

public interface ClientMapper {

    Client converteParaCliente(Object object);
}
