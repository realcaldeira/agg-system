package br.com.aggransystem.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String loginGeral;
    private String loginUser;
    private String passwordUser;

    private Boolean isNetflix;
    private Boolean isHboMax;
    private Boolean isDisneyPlus;
    private Boolean isPremiere;
    private Boolean isPrimeVideo;

    @ManyToMany(mappedBy = "beneficios")
    private Set<Colaborador> colaboradores;

}