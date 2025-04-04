package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.AtualizarHoraSaidaDTO;
import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.repository.ComandaRepository;
import br.bom.techmeal.academic.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<ComandaDTO> listarTodos(){
        return comandaService.listarTodos();
    }

    @GetMapping("/ultima/{clienteId}")
    public ResponseEntity<ComandaDTO> buscarUltimaComandaAtiva(@PathVariable Integer clienteId) {
        // Verifica se o cliente já tem uma comanda ativa (horaSaidaComanda == NULL)
        Optional<Comanda> comandaAtiva = comandaRepository.findComandaAtivaPorCliente(clienteId);

        // Se o cliente tem uma comanda ativa, retorna ela
        if (comandaAtiva.isPresent()) {
            return ResponseEntity.ok(new ComandaDTO(comandaAtiva.get()));
        }

        // Se não tem comanda ativa, retorna um status de "não encontrada"
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Ou retorna uma mensagem personalizada
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
