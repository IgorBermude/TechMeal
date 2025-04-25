package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.AtualizarHoraSaidaDTO;
import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.repository.ComandaRepository;
import br.bom.techmeal.academic.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageRequest;







import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/comanda")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @Autowired
    ComandaRepository comandaRepository;

    @GetMapping
    public List<ComandaDTO> listarTodos() {
        return comandaService.listarTodos();
    }

    @GetMapping("/ultima-finalizada/{clienteId}")
    public ResponseEntity<ComandaDTO> buscarUltimaComandaFinalizada(@PathVariable Integer clienteId) {
        Pageable pageable = PageRequest.of(0, 1); // página 0, 1 resultado
        List<Comanda> comandas = comandaRepository.findUltimaComandaFinalizada(clienteId, pageable);

        if (comandas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(new ComandaDTO(comandas.get(0)));
    }


    // Buscar comanda ativa (sem hora de saída)
    @GetMapping("/ultima/{clienteId}")
    public ResponseEntity<ComandaDTO> buscarUltimaComandaAtiva(@PathVariable Integer clienteId) {
        Optional<Comanda> comandaAtiva = comandaRepository.findComandaAtivaPorCliente(clienteId);

        if (comandaAtiva.isPresent()) {
            return ResponseEntity.ok(new ComandaDTO(comandaAtiva.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @PostMapping
    public ResponseEntity<?> criarComanda(@RequestBody ComandaDTO comandaDTO) {
        try {
            ComandaDTO novaComanda = comandaService.criarComanda(comandaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaComanda);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("erro", e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("erro", "Erro inesperado"));
        }
    }

    @PutMapping
    public ComandaDTO alterar(@RequestBody ComandaDTO comanda) {
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
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        comandaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
