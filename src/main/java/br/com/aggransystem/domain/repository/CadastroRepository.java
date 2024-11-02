package br.com.aggransystem.domain.repository;

import br.com.aggransystem.domain.entity.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
