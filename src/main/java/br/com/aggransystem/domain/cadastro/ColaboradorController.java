package br.com.aggransystem.domain.cadastro;

import br.com.aggransystem.domain.cadastro.entity.Colaborador;
import br.com.aggransystem.domain.service.ResourceNotFoundException;
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
    public ResponseEntity<Colaborador> createColaborador(@RequestBody Colaborador request) {
        Colaborador colaborador = colaboradorService.saveColaborador(request);
        return ResponseEntity.ok(colaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateColaborador(@PathVariable("id") Long id, @RequestBody Colaborador colaborador) {

        boolean cpfExists = colaboradorService.cpfExistsForDifferentColaborador(colaborador.getCpf(), id);
        if (!cpfExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erro: CPF inválido");
        }

        Colaborador updatedColaborador = colaboradorService.saveColaborador(colaborador);
        return ResponseEntity.ok(updatedColaborador);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColaborador(@PathVariable Long id) throws ResourceNotFoundException {
        colaboradorService.deleteColaborador(id);
        return ResponseEntity.ok("Colaborador desativado com sucesso.");
    }


}
