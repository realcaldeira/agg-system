package br.com.aggransystem.domain.service;

import br.com.aggransystem.domain.entity.Beneficio;
import br.com.aggransystem.domain.repository.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepository;

    @Transactional
    public Beneficio atualizarBeneficio(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }
}
