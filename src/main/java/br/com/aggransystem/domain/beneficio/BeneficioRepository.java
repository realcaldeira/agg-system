// BeneficioRepository.java
package br.com.aggransystem.domain.beneficio;

import br.com.aggransystem.domain.beneficio.entity.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
}
