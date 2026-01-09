package com.TrabalhoBD.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrabalhoBD.clinica.exceptions.NotFoundException;
import com.TrabalhoBD.clinica.models.Consulta;
import com.TrabalhoBD.clinica.repositories.ConsultaRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta findById(Long id){
        Optional<Consulta> consulta = this.consultaRepository.findById(id);
        return consulta.orElseThrow( () -> new NotFoundException("Consulta de id = " + id + " não encontrada"));
    }

    public List<Consulta> findAll(){
        List<Consulta> list = this.consultaRepository.findAll();

        if(list.isEmpty()){
            throw new NotFoundException("Nenhuma consulta encontrada");
        }
        return list;
    }

    @Transactional
    public void createConsulta(Consulta consulta){
        this.consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta updateConsulta(Consulta consulta){
        Consulta newConsulta = this.findById(consulta.getId());

        newConsulta.setData(consulta.getData());
        newConsulta.setHorario(consulta.getHorario());
        newConsulta.setObservacoes(consulta.getObservacoes());

        return this.consultaRepository.save(newConsulta);
    }

    public void deleteConsulta(Long id){
        findById(id);

        try{
            this.consultaRepository.deleteById(id);
        }
        catch(org.springframework.dao.DataIntegrityViolationException exception){
            throw new com.TrabalhoBD.clinica.exceptions.DataIntegrityViolationException("Não é possível excluir, pois a consulta possui vinculações");
        }
    }
}
