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

    public Colaborador updateColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }


    public void deleteColaborador(Long id) throws ResourceNotFoundException {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        if (colaborador.isPresent()) {
            Colaborador colaboradorToUpdate = colaborador.get();
            colaboradorToUpdate.setAtivo(false);
            colaboradorRepository.save(colaboradorToUpdate);
        } else {
            throw new ResourceNotFoundException("Colaborador com id " + id + " não encontrado");
        }
    }

    public Colaborador findColaboradorById(Long id) {
        return colaboradorRepository.findById(id).get();
    }


    public boolean existsByCpf(String cpf) {
        return colaboradorRepository.existsByCpf(cpf);
    }


    public Optional<Colaborador> findByCpf(String cpf) {
        return colaboradorRepository.findByCpf(cpf);
    }

    public void deactivateColaborador(Long id) {
    }
}
