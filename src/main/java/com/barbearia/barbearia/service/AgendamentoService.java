package com.barbearia.barbearia.service;

import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public Agendamento salvar(Agendamento agendamento) {
        validarAgendamento(agendamento);
        return repository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }

    private void validarAgendamento(Agendamento agendamento) {
        if (agendamento.getNome() == null || agendamento.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        // Adicione mais validações conforme necessário
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Método para buscar um agendamento por ID
    public Optional<Agendamento> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
