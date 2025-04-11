package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.dto.HistoricoRecargaDTO;
import br.bom.techmeal.academic.service.HistoricoRecargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/historico-recarga")
public class HistoricoRecargaController {

    @Autowired
    private HistoricoRecargaService historicoRecargaService;

    @GetMapping
    public List<HistoricoRecargaDTO> listarTodos() {
        return historicoRecargaService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody HistoricoRecargaDTO historicoRecarga) {
        historicoRecargaService.inserir(historicoRecarga);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        historicoRecargaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public HistoricoRecargaDTO buscarHistoricoRecargaPorId(@PathVariable Integer id) {
        return historicoRecargaService.buscarPorId(id);
    }

    public HistoricoRecargaDTO alterar(@RequestBody HistoricoRecargaDTO historicoRecarga) {
        return historicoRecargaService.alterar(historicoRecarga);
    }

}
