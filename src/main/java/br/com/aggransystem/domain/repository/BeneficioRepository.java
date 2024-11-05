package br.com.aggransystem.domain.repository;

import br.com.aggransystem.domain.entity.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
    // Métodos de consulta adicionais, se necessário
}
