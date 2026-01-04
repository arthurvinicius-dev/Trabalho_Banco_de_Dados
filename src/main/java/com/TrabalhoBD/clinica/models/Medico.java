package com.TrabalhoBD.clinica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Entity(name="medico")
@Table(name="medicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;

    @Column(name="CRM", unique = true)
    private String crm;

    @Column(name = "nome" , nullable = false, length = 250)
    private String nome;

    
    @Column(name = "especialidade")
    private String especialidade;
    
}
