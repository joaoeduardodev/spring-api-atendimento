package com.atendimento.api.mapper;

import com.atendimento.api.domain.Cliente;
import com.atendimento.api.request.ClientePostRequestBody;
import com.atendimento.api.request.ClientePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring")
public abstract class ClienteMapper {
    public  static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    public abstract Cliente ToCliente(ClientePostRequestBody clientePostRequestBody);
    public abstract Cliente ToCliente(ClientePutRequestBody clientePutRequestBodyRequestBody);
}
