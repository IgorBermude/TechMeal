package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> listarTodos(){
        return produtoService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody ProdutoDTO produto){
        produtoService.inserir(produto);
    }

    @PutMapping
    public ProdutoDTO alterar(@RequestBody ProdutoDTO produto){
        return produtoService.alterar(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        produtoService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
