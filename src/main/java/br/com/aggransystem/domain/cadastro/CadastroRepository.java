package br.com.aggransystem.domain.cadastro;

import br.com.aggransystem.domain.cadastro.entity.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
