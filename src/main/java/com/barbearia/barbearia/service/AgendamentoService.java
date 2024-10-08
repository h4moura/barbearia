package com.barbearia.barbearia.service;

import com.barbearia.barbearia.model.Agendamento;
import com.barbearia.barbearia.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public Agendamento salvar(Agendamento agendamento){
        //Logica de validação pode ser aplicada aqui!!!
        return repository.save(agendamento);
    }

    public List<Agendamento> listarTodos(){
        return repository.findAll();
    }
}
