package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarTodos(){
        return clienteService.listarTodos();
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
        clienteService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
