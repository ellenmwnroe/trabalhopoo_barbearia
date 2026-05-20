package com.barberconnect.api.controller;

import com.barberconnect.api.dto.BarbeiroRequestDTO; // <-- Importação do DTO
import com.barberconnect.api.model.Barbeiro;
import com.barberconnect.api.service.BarbeiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {

    private final BarbeiroService service;

    public BarbeiroController(BarbeiroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Barbeiro>> listarBarbeiros() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Atualizado para receber o DTO
    @PostMapping
    public ResponseEntity<Barbeiro> criarBarbeiro(@RequestBody BarbeiroRequestDTO dto) {
        Barbeiro novoBarbeiro = service.salvar(dto);
        return ResponseEntity.status(201).body(novoBarbeiro);
    }
}