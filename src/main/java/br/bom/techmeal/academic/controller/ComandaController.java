package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.AtualizarHoraSaidaDTO;
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

    @GetMapping("/ultima/{clienteId}")
    public ResponseEntity<ComandaDTO> buscarUltimaComandaAtiva(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(comandaService.buscarUltimaComandaAtiva(clienteId));
    }



    @PostMapping
    public ResponseEntity<ComandaDTO> criarComanda(@RequestBody ComandaDTO comandaDTO) {
        ComandaDTO novaComanda = comandaService.criarComanda(comandaDTO);
        return ResponseEntity.ok(novaComanda);
    }

    @PutMapping
    public ComandaDTO alterar(@RequestBody ComandaDTO comanda){
        return comandaService.alterar(comanda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComandaDTO> atualizarComanda(@PathVariable Integer id, @RequestBody ComandaDTO comandaDTO) {
        return ResponseEntity.ok(comandaService.atualizarComanda(id, comandaDTO));
    }




    @PutMapping("/saida/{id}")
    public ResponseEntity<ComandaDTO> atualizarHoraSaida(@PathVariable Integer id, @RequestBody AtualizarHoraSaidaDTO requestBody) {
        return ResponseEntity.ok(comandaService.atualizarHoraSaida(id, requestBody.getHoraSaidaComanda()));
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        comandaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
