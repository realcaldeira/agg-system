package br.com.aggransystem.domain.cadastro;

import br.com.aggransystem.domain.beneficio.BeneficioRepository;
import br.com.aggransystem.domain.beneficio.entity.Beneficio;
import br.com.aggransystem.domain.cadastro.entity.Colaborador;
import br.com.aggransystem.domain.service.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final BeneficioRepository beneficioRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository, BeneficioRepository beneficioRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.beneficioRepository = beneficioRepository;
    }

    public List<Colaborador> getAllColaboradores() {
        return colaboradorRepository.findAll();
    }

    public Colaborador saveColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public void deleteColaborador(Long id) {
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


    public Colaborador updateColaboradorByCpf(String cpf, Colaborador updatedData) throws ResourceNotFoundException {
        Optional<Colaborador> optionalColaborador = colaboradorRepository.findByCpf(cpf);
        if (optionalColaborador.isPresent()) {
            Colaborador colaborador = optionalColaborador.get();


            colaborador.setNome(updatedData.getNome());
            colaborador.setDataNascimento(updatedData.getDataNascimento());
            colaborador.setEmail(updatedData.getEmail());
            colaborador.setAtivo(updatedData.getAtivo());


            Set<Beneficio> beneficios = updatedData.getBeneficios();
            if (beneficios != null) {
                beneficioRepository.saveAll(beneficios);
                colaborador.setBeneficios(beneficios);
            }

            return colaboradorRepository.save(colaborador);
        } else {
            throw new ResourceNotFoundException("Colaborador com CPF " + cpf + " não encontrado.");
        }
    }
}
