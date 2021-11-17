package com.atendimento.api.service;

import com.atendimento.api.domain.Cliente;
import com.atendimento.api.mapper.ClienteMapper;
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

    public List<Cliente> findByNome(String nome){
        return clienteRepository.findByNome(nome);
    }

    public Cliente findByIdOrThrowBadRequestException(long id){
        return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente não encontrado"));
    }

    public Cliente save(ClientePostRequestBody clientePostRequestBody) {
        return clienteRepository.save(ClienteMapper.INSTANCE.ToCliente(clientePostRequestBody));
    }

    public void delete(long id) {
        clienteRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ClientePutRequestBody clientePutRequestBody) {


        Cliente savedCliente = findByIdOrThrowBadRequestException(clientePutRequestBody.getId());

        Cliente cliente = ClienteMapper.INSTANCE.ToCliente(clientePutRequestBody);

        cliente.setId(savedCliente.getId());

        clienteRepository.save(cliente);
    }
}
