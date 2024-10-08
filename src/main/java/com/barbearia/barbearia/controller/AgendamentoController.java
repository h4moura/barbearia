package com.barbearia.barbearia.controller;

import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoSalvo = agendamentoService.salvar(agendamento);
        return ResponseEntity.ok(agendamentoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.listarTodos();
        return ResponseEntity.ok(agendamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long id) {
        agendamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Novo método para reagendar um agendamento (apenas atualiza data e horário)
    @PutMapping("/{id}/reagendar")
    public ResponseEntity<Agendamento> reagendarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Optional<Agendamento> agendamentoExistente = agendamentoService.buscarPorId(id);

        if (agendamentoExistente.isPresent()) {
            Agendamento agendamentoAtualizado = agendamentoExistente.get();
            agendamentoAtualizado.setData(agendamento.getData()); // Atualiza apenas a data
            agendamentoAtualizado.setHorario(agendamento.getHorario()); // Atualiza apenas o horário

            agendamentoService.salvar(agendamentoAtualizado); // Salva as alterações no banco de dados
            return ResponseEntity.ok(agendamentoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
