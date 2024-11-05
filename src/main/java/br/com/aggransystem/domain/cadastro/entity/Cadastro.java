package br.com.aggransystem.domain.cadastro.entity;

import br.com.aggransystem.domain.beneficio.entity.Beneficio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCadastro;

    @ManyToOne
    private Colaborador colaborador;

    @ManyToOne
    private Beneficio beneficio;


}
