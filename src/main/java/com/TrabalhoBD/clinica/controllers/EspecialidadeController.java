package com.TrabalhoBD.clinica.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.TrabalhoBD.clinica.models.Especialidade;
import com.TrabalhoBD.clinica.services.EspecialidadeService;
import com.TrabalhoBD.clinica.services.MedicoService;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
    
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> findById (@PathVariable Long id){
        Especialidade obj = this.especialidadeService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<Especialidade>> findAllByMedicoId (@PathVariable Long idMedico){
        this.medicoService.findById(idMedico);
        List<Especialidade> objs = this.especialidadeService.findByMedicoId(idMedico);
        return ResponseEntity.ok().body(objs); 
    }

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody Especialidade obj){
        this.especialidadeService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update (@RequestBody Especialidade obj, @PathVariable Long id){
        obj.setId(id);
        this.especialidadeService.update(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        this.especialidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
