package com.atendimento.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.atendimento.api.request.ClientePostRequestBody;
import com.atendimento.api.request.ClientePutRequestBody;
import com.atendimento.api.service.ClienteService;
import com.atendimento.api.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atendimento.api.domain.Cliente;

@RestController
@RequestMapping("clientes")
@Log4j2
@RequiredArgsConstructor
public class ClienteController {

	private final DateUtil dateUtil;
	private final ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> list(){
		log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok(clienteService.listAll()) ;
		
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable long id){
		return ResponseEntity.ok(clienteService.findByIdOrThrowBadRequestException(id)) ;

	}

	@PostMapping
	public  ResponseEntity<Cliente> save(@RequestBody ClientePostRequestBody clientePostRequestBody){
		return new ResponseEntity<>(clienteService.save(clientePostRequestBody), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public  ResponseEntity<Void> delete(@PathVariable long id){
		clienteService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public  ResponseEntity<Void> replace(@RequestBody ClientePutRequestBody clientePutRequestBody){
		clienteService.replace(clientePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
