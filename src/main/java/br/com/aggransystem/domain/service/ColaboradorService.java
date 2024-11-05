// ColaboradorService.java
package br.com.aggransystem.domain.service;


import br.com.aggransystem.domain.entity.Colaborador;
import br.com.aggransystem.domain.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<Colaborador> getAllColaboradores() {
        return colaboradorRepository.findAll();
    }

    public Colaborador saveColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }


    public void deleteColaborador(Long id) throws ResourceNotFoundException {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        if (colaborador.isPresent()) {
            Colaborador colaboradorToUpdate = colaborador.get();
            colaboradorToUpdate.setAtivo(false);
            colaboradorRepository.save(colaboradorToUpdate);
        }
    }


    public Optional<Colaborador> findByCpf(String cpf) {
        return colaboradorRepository.findByCpf(cpf);
    }


    public boolean cpfExistsForDifferentColaborador(String cpf, Long id) {
        return colaboradorRepository.existsByCpfAndIdNot(cpf, id);
    }
}
