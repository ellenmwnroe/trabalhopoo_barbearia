package com.barberconnect.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberconnect.api.model.Cliente;
import com.barberconnect.api.service.ClienteService;

@RestController
@RequestMapping("/clientes") // A rota base será algo como http://localhost:8080/clientes
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // Rota GET: Retorna a lista de clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = service.listarTodos();
        return ResponseEntity.ok(clientes); // Retorna Status 200 OK
    }

    // Rota POST: Recebe um JSON e cria um cliente novo
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = service.salvar(cliente);
        return ResponseEntity.status(201).body(novoCliente); // Retorna Status 201 Created
    }
}