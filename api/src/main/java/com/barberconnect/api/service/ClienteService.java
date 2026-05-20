package com.barberconnect.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barberconnect.api.model.Cliente;
import com.barberconnect.api.repository.ClienteRepository;

@Service
public class ClienteService {

    // A injeção de dependência: O Service chama o Repository para falar com o banco
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    // Método para listar todos os clientes
    public List<Cliente> listarTodos() {
        return repository.findAll(); // O JPA faz o SELECT * FROM clientes sozinho
    }

    // Método para salvar um novo cliente
    public Cliente salvar(Cliente cliente) {
        // Aqui você poderia colocar regras, ex: verificar se o email já existe
        return repository.save(cliente); // O JPA faz o INSERT INTO sozinho
    }
}