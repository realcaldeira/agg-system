package br.com.aggransystem.domain.repository;

import br.com.aggransystem.domain.entity.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
}
