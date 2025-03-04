package com.higorbeleza.worktime.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.higorbeleza.worktime.model.RegistroDePonto;
import com.higorbeleza.worktime.service.RegistroDePontoService;

@RestController
@RequestMapping("/api/registro-ponto")
public class RegistroDePontoController {
    
    @Autowired
    private RegistroDePontoService service;

    // Registrar entrada
    @PostMapping("/entrada")
    public RegistroDePonto registrarEntrada(@RequestParam String funcionario, @RequestParam String entrada, @RequestParam String data) {
        LocalTime entradaTime = LocalTime.parse(entrada);
        LocalDate dataDate = LocalDate.parse(data);
        return service.registrarEntrada(funcionario, dataDate, entradaTime);
    }

    // Registrar saída para o almoço
    @PostMapping("/saida-almoco/{id}")
    public RegistroDePonto registrarSaidaAlmoco(@PathVariable Long id, @RequestParam String saidaAlmoco) {
        LocalTime saidaAlmocoTime = LocalTime.parse(saidaAlmoco);
        return service.registrarSaidaAlmoco(id, saidaAlmocoTime);
    }

    // Registrar retorno do almoço
    @PostMapping("/retorno-almoco/{id}")
    public RegistroDePonto registrarRetornoAlmoco(@PathVariable Long id, @RequestParam String retornoAlmoco) {
        LocalTime retornoAlmocoTime = LocalTime.parse(retornoAlmoco);
        return service.registrarRetornoAlmoco(id, retornoAlmocoTime);
    }

    // Registrar saída final
    @PostMapping("/saida/{id}")
    public RegistroDePonto registrarSaida(@PathVariable Long id, @RequestParam String saida) {
        LocalTime saidaTime = LocalTime.parse(saida);
        return service.registrarSaida(id, saidaTime);
    }

    // Calcular horas trabalhadas
    @GetMapping("/horas/{id}")
    public long calcularHorasTrabalhadas(@PathVariable Long id) {
        return service.calcularHorasTrabalhadas(id);
    }
}
