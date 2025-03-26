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
        controleContasService.atualizarContasVencidas(); //
        return controleContasService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody ControleContasDTO controleContas) {
        controleContasService.inserir(controleContas);
    }

    @PutMapping
    public ControleContasDTO alterar(@RequestBody ControleContasDTO controleContas) {
        return controleContasService.alterar(controleContas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        controleContasService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
