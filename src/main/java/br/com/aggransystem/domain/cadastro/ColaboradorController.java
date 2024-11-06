
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
    public ResponseEntity<?> createColaborador(@RequestBody Colaborador request) {
        if (colaboradorService.findByCpf(request.getCpf()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um colaborador cadastrado com o CPF " + request.getCpf());
        }

        Colaborador colaborador = colaboradorService.saveColaborador(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaborador);
    }


    @PutMapping("/{cpf}")
    public ResponseEntity<?> updateColaborador(@PathVariable("cpf") String cpf, @RequestBody Colaborador request) {
        try {
            Colaborador updatedColaborador = colaboradorService.updateColaboradorByCpf(cpf, request);
            return ResponseEntity.ok(updatedColaborador);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColaborador(@PathVariable Long id) throws ResourceNotFoundException {
        colaboradorService.deleteColaborador(id);
        return ResponseEntity.ok("Colaborador desativado com sucesso.");
    }
}
