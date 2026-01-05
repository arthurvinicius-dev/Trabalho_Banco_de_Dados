package com.TrabalhoBD.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrabalhoBD.clinica.models.Especialidade;
import com.TrabalhoBD.clinica.models.Medico;
import com.TrabalhoBD.clinica.repositories.EspecialidadeRepository;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private MedicoService medicoService;

    public Especialidade findById(Long id){
        Optional<Especialidade> especialidade = this.especialidadeRepository.findById(id);

        return especialidade.orElseThrow( () -> new RuntimeException(
            "Especialidade não encontrada"
        ));
    }

    public List<Especialidade> findByMedicoId(Long idMedico){
        List<Especialidade> especialidades = this.especialidadeRepository.findByMedicos_Id(idMedico);
        return especialidades;
    }

    public Especialidade create(Especialidade obj){
        obj.setId(null);
        obj = this.especialidadeRepository.save(obj);
        return obj;
    }

    public Especialidade update(Especialidade obj){
        Especialidade newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        return this.especialidadeRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);

        try{
            this.especialidadeRepository.deleteById(id);
        }
        catch(Exception e){
            throw new RuntimeException("Não é possível deletar");
        }
    }
}
