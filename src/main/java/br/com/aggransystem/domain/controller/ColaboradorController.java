package br.com.aggransystem.domain.controller;

import br.com.aggransystem.domain.entity.Colaborador;
import br.com.aggransystem.domain.service.ColaboradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping
    public List<Colaborador> getAllColaboradores() {
        return colaboradorService.getAllColaboradores();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getColaboradorByCpf(@PathVariable String cpf) {
        Optional<Colaborador> colaborador = colaboradorService.findByCpf(cpf);
        if (colaborador.isPresent()) {
            return ResponseEntity.ok(colaborador.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Colaborador com o CPF " + cpf + " não encontrado.");
        }
    }


    @PostMapping
    public ResponseEntity<?> createColaborador(@RequestBody Colaborador colaborador) {
        if (colaboradorService.existsByCpf(colaborador.getCpf())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Já existe um colaborador com o CPF informado.");
        }
        Colaborador novoColaborador = colaboradorService.saveColaborador(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);
    }

    @PutMapping("/{id}")
    public Colaborador updateColaborador(@PathVariable("id") Long id, @RequestBody Colaborador colaborador) {
        return colaboradorService.updateColaborador(colaborador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColaborador(@PathVariable Long id) {
        colaboradorService.deactivateColaborador(id);
        return ResponseEntity.ok("Colaborador desativado com sucesso.");
    }


}
