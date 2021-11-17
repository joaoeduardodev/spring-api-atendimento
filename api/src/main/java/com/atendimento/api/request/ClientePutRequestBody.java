package com.atendimento.api.request;

import lombok.Data;

@Data
public class ClientePutRequestBody {
    private Long id;
    private String nome;
}
