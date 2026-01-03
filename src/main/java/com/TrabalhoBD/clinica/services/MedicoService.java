package com.TrabalhoBD.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrabalhoBD.clinica.models.Medico;
import com.TrabalhoBD.clinica.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;


    public Medico findById(String crm){
        Optional<Medico> medico = this.medicoRepository.findById(crm);

        return medico.orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }

    public List<Medico> findAllMedicos (){
        Optional<List<Medico>> list = Optional.ofNullable(this.medicoRepository.findAll());

        return list.orElseThrow( () -> new RuntimeException("Lista de médicos não encontradas"));
    }

    public void createMedico(Medico medico){
        this.medicoRepository.save(medico);
    }

    public Medico updateMedico(Medico medico){
        Medico newMedico = findById(medico.getCrm());
        newMedico.setNome(medico.getNome());
        newMedico.setIdade(medico.getIdade());
        return this.medicoRepository.save(newMedico);
    }

    public void deleteMedico(String crm){
        findById(crm);

        try {
            this.medicoRepository.deleteById(crm);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir");
        }
    }


}
