package com.atendimento.api.repository;

import com.atendimento.api.domain.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository {
    List<Cliente> ListAll();
}
