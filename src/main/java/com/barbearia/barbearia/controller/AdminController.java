package com.barbearia.barbearia.controller;

import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Use @RestController para simplificar o uso de @Controller e @ResponseBody
@CrossOrigin(origins = "http://localhost:3000") // Altere conforme necessário para permitir acesso CORS
@RequestMapping("/api/admin/agendamentos") // Define o caminho base para os endpoints deste controlador
public class AdminController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // Endpoint para listar todos os agendamentos
    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return ResponseEntity.ok(agendamentos);
    }
}
