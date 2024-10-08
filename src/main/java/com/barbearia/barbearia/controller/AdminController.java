package com.barbearia.barbearia.controller;


import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/admin")
    public String adminPage(Model model){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        model.addAttribute("agendamentos", agendamentos);
        return "admin";
    }

}
