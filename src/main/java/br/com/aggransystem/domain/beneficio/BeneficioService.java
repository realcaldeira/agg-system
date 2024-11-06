package br.com.aggransystem.domain.beneficio;

import br.com.aggransystem.domain.beneficio.entity.Beneficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepository;

    @Transactional
    public Beneficio updateBeneficio(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }
}
