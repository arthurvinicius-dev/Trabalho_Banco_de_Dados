package com.TrabalhoBD.clinica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TrabalhoBD.clinica.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Optional<Paciente> findByNome(String nome);
}
