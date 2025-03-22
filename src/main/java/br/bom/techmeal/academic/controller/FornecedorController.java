package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.service.ClienteService;
import br.bom.techmeal.academic.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<FornecedorDTO> listarTodos(){
        return fornecedorService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody FornecedorDTO fornecedor){
        fornecedorService.inserir(fornecedor);
    }

    @PutMapping
    public FornecedorDTO alterar(@RequestBody FornecedorDTO fornecedor){
        return fornecedorService.alterar(fornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        fornecedorService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
