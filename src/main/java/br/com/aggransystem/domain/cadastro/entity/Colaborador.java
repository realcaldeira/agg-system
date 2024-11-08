package br.com.aggransystem.domain.cadastro.entity;

import br.com.aggransystem.domain.beneficio.entity.Beneficio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private Boolean ativo = true;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "colaborador_beneficio",
            joinColumns = @JoinColumn(name = "colaborador_id"),
            inverseJoinColumns = @JoinColumn(name = "beneficio_id")
    )
    private Set<Beneficio> beneficios;


    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


}
