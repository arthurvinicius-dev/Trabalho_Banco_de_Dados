package com.TrabalhoBD.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TrabalhoBD.clinica.models.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long>{
    List<Especialidade> findByMedicos_Id(Long id);
}
