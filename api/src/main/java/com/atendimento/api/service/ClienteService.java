package com.atendimento.api.service;

import com.atendimento.api.domain.Cliente;
import com.atendimento.api.repository.ClienteRepository;
import com.atendimento.api.request.ClientePostRequestBody;
import com.atendimento.api.request.ClientePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listAll(){
        return clienteRepository.findAll();
    }

    public Cliente findByIdOrThrowBadRequestException(long id){
        return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente não encontrado"));
    }

    public Cliente save(ClientePostRequestBody clientePostRequestBody) {
        Cliente cliente = Cliente.builder().nome(clientePostRequestBody.getNome()).build();

        return clienteRepository.save(cliente);
    }

    public void delete(long id) {
        clienteRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ClientePutRequestBody clientePutRequestBody) {

        Cliente savedCliente = findByIdOrThrowBadRequestException(clientePutRequestBody.getId());

        Cliente cliente = Cliente.builder()
                .id(savedCliente.getId())
                .nome(clientePutRequestBody.getNome())
                .build();

        clienteRepository.save(cliente);
    }
}
