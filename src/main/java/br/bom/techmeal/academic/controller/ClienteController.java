package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ClienteDTO;

import br.bom.techmeal.academic.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.bom.techmeal.academic.service.ClienteService.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarTodos(){
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id) {
        ClienteDTO cliente = clienteService.buscarPorId(id); // Serviço que busca a conta pelo id

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 caso não encontre
        }
    }

    @PostMapping
    public void inserir(@RequestBody ClienteDTO cliente){
        clienteService.inserir(cliente);
    }

    @PutMapping
    public ClienteDTO alterar(@RequestBody ClienteDTO cliente){
        return clienteService.alterar(cliente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        try {
            clienteService.excluir(id);
            return ResponseEntity.ok().build(); // Retorna 200 OK se a exclusão for bem-sucedida
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se a conta não for encontrada
        }
    }
}
