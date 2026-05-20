package com.barberconnect.api.service;

import com.barberconnect.api.dto.BarbeiroRequestDTO;
import com.barberconnect.api.model.Barbeiro;
import com.barberconnect.api.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeiroService {

    private final BarbeiroRepository repository;

    public BarbeiroService(BarbeiroRepository repository) {
        this.repository = repository;
    }

    public List<Barbeiro> listarTodos() {
        return repository.findAll();
    }

    public Barbeiro salvar(BarbeiroRequestDTO dto) {
        
        Barbeiro barbeiro = new Barbeiro(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getEspecialidade(),
                dto.getPercentualComissao()
        );

        return repository.save(barbeiro);
    }
}