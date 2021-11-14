package com.atendimento.api.service;

import com.atendimento.api.domain.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ClienteService {

    private static List<Cliente> clientes;

    static {
        clientes =  new ArrayList<>(List.of(new Cliente(1L,"jjjj"),new Cliente(2L,"Joao")));
    }

    public List<Cliente> listAll(){
        return clientes;
    }
    public Cliente findById(long id){
        return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente não encontrado"));
    }

    public Cliente save(Cliente cliente) {
        cliente.setId(ThreadLocalRandom.current().nextLong(3,100));
        clientes.add(cliente);
        return cliente;
    }

    public void delete(long id) {
        clientes.remove(findById(id));
    }

    public void replace(Cliente cliente) {
        delete(cliente.getId());
        clientes.add(cliente);
    }
}
