package com.higorbeleza.worktime.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.higorbeleza.worktime.model.RegistroDePonto;

public interface RegistroDePontoRepository extends JpaRepository<RegistroDePonto, Long> {
    @Query("SELECT r FROM RegistroDePonto r WHERE r.data BETWEEN :inicio AND :fim")
    List<RegistroDePonto> findByDataBetween(LocalDate inicio, LocalDate fim);
}
