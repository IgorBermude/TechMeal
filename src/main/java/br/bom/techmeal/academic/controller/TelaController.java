package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.TelaDTO;
import br.bom.techmeal.academic.dto.UsuarioDTO;
import br.bom.techmeal.academic.service.TelaService;
import br.bom.techmeal.academic.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tela")
public class TelaController {
    @Autowired
    private TelaService telaService;

    @GetMapping
    public List<TelaDTO> listarTodos(){
        return telaService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody TelaDTO tela){
        telaService.inserir(tela);
    }

    @PutMapping
    public TelaDTO alterar(@RequestBody TelaDTO tela){
        return telaService.alterar(tela);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        telaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelaDTO> buscarPorId(@PathVariable Integer id){
        TelaDTO tela = telaService.buscarPorId(id);
        return tela != null ? ResponseEntity.ok(tela) : ResponseEntity.notFound().build();
    }
}
