package com.barbearia.barbearia.service;

import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
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

        // Pegar o dia da semana
        DayOfWeek diaDaSemana = agendamento.getData().getDayOfWeek();
        LocalTime horario = agendamento.getHorario();

        // Regras para terça e quinta (09:00 às 17:00)
        if (diaDaSemana == DayOfWeek.TUESDAY || diaDaSemana == DayOfWeek.THURSDAY) {
            if (horario.isBefore(LocalTime.of(9, 0)) || horario.isAfter(LocalTime.of(17, 0))) {
                throw new IllegalArgumentException("Agendamentos em terça e quinta são permitidos apenas das 09:00 às 17:00.");
            }
        }

        // Regras para quarta, sexta e sábado (09:00 às 20:00)
        if (diaDaSemana == DayOfWeek.WEDNESDAY || diaDaSemana == DayOfWeek.FRIDAY || diaDaSemana == DayOfWeek.SATURDAY) {
            if (horario.isBefore(LocalTime.of(9, 0)) || horario.isAfter(LocalTime.of(20, 0))) {
                throw new IllegalArgumentException("Agendamentos em quarta, sexta e sábado são permitidos apenas das 09:00 às 20:00.");
            }
        }

        // Não permitir agendamentos aos domingos e segundas-feiras
        if (diaDaSemana == DayOfWeek.SUNDAY || diaDaSemana == DayOfWeek.MONDAY) {
            throw new IllegalArgumentException("Agendamentos não são permitidos aos domingos e segundas-feiras.");
        }
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Método para buscar um agendamento por ID
    public Optional<Agendamento> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
