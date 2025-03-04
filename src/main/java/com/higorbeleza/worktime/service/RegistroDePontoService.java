package com.higorbeleza.worktime.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.higorbeleza.worktime.model.RegistroDePonto;
import com.higorbeleza.worktime.repository.RegistroDePontoRepository;

@Service
public class RegistroDePontoService {
    private static final double VALOR_HORA = 6.90;

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

    public List<RegistroDePonto> buscarRegistrosPorMes(int ano, Month mes) {
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());
        return repository.findByDataBetween(inicio, fim);
    }
    
    public long calcularTotalHorasNoMes(int ano, Month mes) {
        List<RegistroDePonto> registros = buscarRegistrosPorMes(ano, mes);
        long totalHoras = 0;

        for (RegistroDePonto registro : registros) {
            totalHoras += calcularHorasTrabalhadas(registro.getId());
        }

        return totalHoras;
    }
    
    public long calcularTotalMinutosNoMes(int ano, Month mes) {
        List<RegistroDePonto> registros = buscarRegistrosPorMes(ano, mes);
        long totalMinutos = 0;

        for (RegistroDePonto registro : registros) {
            totalMinutos += calcularMinutosTrabalhados(registro.getId());
        }

        return totalMinutos;
    }

    public long calcularMinutosTrabalhados(Long id) {
        RegistroDePonto registro = repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        // Calcule os minutos trabalhados com base nas entradas e saídas
        long minutosTrabalhados = 0;

        // Suponha que você tenha entradas e saídas registradas como LocalTime
        LocalTime entrada = registro.getEntrada(); 
        LocalTime saida = registro.getSaida(); 
        
        // Calculando a diferença em minutos
        if (entrada != null && saida != null) {
            Duration duracao = Duration.between(entrada, saida);
            minutosTrabalhados = duracao.toMinutes();
        }

        return minutosTrabalhados;
    }

    // Método para calcular o pagamento baseado nos minutos trabalhados (R$ 6,90 por hora)
    public double calcularPagamento(Long id) {
        long minutosTrabalhados = calcularMinutosTrabalhados(id);
        double horasTrabalhadas = minutosTrabalhados / 60.0;
        return horasTrabalhadas * VALOR_HORA;
    }

    // Método para calcular o pagamento total no mês
    public double calcularPagamentoTotalNoMes(int ano, Month mes) {
        long totalMinutos = calcularTotalMinutosNoMes(ano, mes);
        double horasTrabalhadas = totalMinutos / 60.0;
        return horasTrabalhadas * VALOR_HORA;
    }
}
