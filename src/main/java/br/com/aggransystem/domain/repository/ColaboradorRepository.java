package br.com.aggransystem.domain.repository;

import br.com.aggransystem.domain.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    boolean existsByCpf(String cpf);
    Optional<Colaborador> findByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);
}
