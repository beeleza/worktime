package com.higorbeleza.worktime.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.higorbeleza.worktime.model.RegistroDePonto;
import com.higorbeleza.worktime.repository.RegistroDePontoRepository;

@Service
public class RegistroDePontoService {
    @Autowired
    private RegistroDePontoRepository repository;

    public RegistroDePonto registrarEntrada(String funcionario, LocalDate data, LocalTime entrada) {
        RegistroDePonto ponto = new RegistroDePonto(funcionario, entrada, null, null, null, data);
        return repository.save(ponto);
    }

    public RegistroDePonto registrarSaidaAlmoco(Long id, LocalTime saidaAlmoco) {
        RegistroDePonto ponto = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        ponto.setSaidaAlmoco(saidaAlmoco);
        return repository.save(ponto);
    }

    public RegistroDePonto registrarRetornoAlmoco(Long id, LocalTime retornoAlmoco) {
        RegistroDePonto ponto = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        ponto.setRetornoAlmoco(retornoAlmoco);
        return repository.save(ponto);
    }

    public RegistroDePonto registrarSaida(Long id, LocalTime saida) {
        RegistroDePonto ponto = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        ponto.setSaida(saida);
        return repository.save(ponto);
    }

    public long calcularHorasTrabalhadas(Long id) {
        RegistroDePonto ponto = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        return ponto.calcularHorasTrabalhadas();
    }
}
