package com.TrabalhoBD.clinica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    @NotBlank
    private String nome;

    @Column(name = "cpf", unique = true, nullable = false, length = 255)
    @NotBlank
    private String cpf;

    @Column(name = "telefone", unique = true, nullable = false, length = 255)
    @NotBlank
    private String telefone;

    @Column(name = "endereço", nullable = false, length = 255)
    @NotBlank
    private String endereco;
}
