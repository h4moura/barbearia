package com.barbearia.barbearia.controller;


import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public Agendamento agendar(@RequestBody Agendamento agendamento){
        return service.salvar(agendamento);
    }

    @GetMapping
    public List<Agendamento> listarAgendamentos(){
        return service.listarTodos();
    }

}

