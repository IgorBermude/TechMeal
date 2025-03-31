package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comanda")
public class ComandaController {
    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public List<ComandaDTO> listarTodos(){
        return comandaService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody ComandaDTO comanda){
        comandaService.inserir(comanda);
    }

    @PutMapping
    public ComandaDTO alterar(@RequestBody ComandaDTO comanda){
        return comandaService.alterar(comanda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        comandaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
