package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.service.ControleContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/controlecontas")
public class ControleContasController {

    @Autowired
    private ControleContasService controleContasService;


    @GetMapping
    public List<ControleContasDTO> listarTodos() {
        controleContasService.atualizarContasVencidas(); // Atualiza as contas vencidas
        return controleContasService.listarTodos();
    }


    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody ControleContasDTO controleContas) {
        controleContasService.inserir(controleContas);
        return ResponseEntity.status(201).build();  // Retorna 201 Created
    }


    @PutMapping
    public ResponseEntity<ControleContasDTO> alterar(@RequestBody ControleContasDTO controleContas) {
        ControleContasDTO updatedConta = controleContasService.alterar(controleContas);
        return ResponseEntity.ok(updatedConta); // Retorna a conta atualizada com 200 OK
    }


    @PutMapping("/pagar/{id}")
    public ResponseEntity<ControleContasDTO> pagarConta(@PathVariable Integer id) {
        try {
            ControleContasDTO contaPaga = controleContasService.pagarConta(id);
            return ResponseEntity.ok(contaPaga); // Retorna a conta paga com 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se a conta não for encontrada
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        try {
            controleContasService.excluir(id);
            return ResponseEntity.ok().build(); // Retorna 200 OK se a exclusão for bem-sucedida
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se a conta não for encontrada
        }
    }
}
