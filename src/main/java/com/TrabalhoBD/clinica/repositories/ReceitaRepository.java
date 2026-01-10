package com.TrabalhoBD.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TrabalhoBD.clinica.models.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
    
    List<Receita> findByConsulta_id(Long id);
}
