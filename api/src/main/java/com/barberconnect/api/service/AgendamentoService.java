package com.barberconnect.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barberconnect.api.dto.AgendamentoRequestDTO;
import com.barberconnect.api.model.Agendamento;
import com.barberconnect.api.model.Barbeiro;
import com.barberconnect.api.model.Cliente;
import com.barberconnect.api.repository.AgendamentoRepository;
import com.barberconnect.api.repository.BarbeiroRepository;
import com.barberconnect.api.repository.ClienteRepository;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;

    // Injetando todos os repositórios necessários para buscar os dados no banco
    public AgendamentoService(AgendamentoRepository repository, 
                              ClienteRepository clienteRepository, 
                              BarbeiroRepository barbeiroRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.barbeiroRepository = barbeiroRepository;
    }

    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }

    // AGORA SIM ELE RECEBE O DTO DO FRONT-END!
    public Agendamento criar(AgendamentoRequestDTO dto) {
        
        // 1. Busca o Cliente de verdade no banco usando o ID que veio no JSON
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + dto.getClienteId()));

        // 2. Busca o Barbeiro de verdade no banco
        Barbeiro barbeiro = barbeiroRepository.findById(dto.getBarbeiroId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado com o ID: " + dto.getBarbeiroId()));

        // 3. Monta o objeto Agendamento (Essa é a POO aplicada na prática!)
        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setStatus("PENDENTE"); // Regra de negócio automatizada

        // 4. Salva no Supabase e retorna
        return repository.save(agendamento);
    }
}