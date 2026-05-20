package com.barberconnect.api.controller;

import com.barberconnect.api.dto.AgendamentoRequestDTO;
import com.barberconnect.api.model.Agendamento;
import com.barberconnect.api.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoRequestDTO request) {
        Agendamento novoAgendamento = service.criar(request);
        return ResponseEntity.status(201).body(novoAgendamento);
    }
}