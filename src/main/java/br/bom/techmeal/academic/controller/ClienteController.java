package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/cartao/{idCartaoCliente}")
    public ResponseEntity<Cliente> buscarClientePorCartao(@PathVariable String idCartaoCliente) {
        Cliente cliente = clienteService.buscarClientePorCartao(idCartaoCliente);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se não encontrar o cliente
        }
    }

    @PostMapping
    public void inserir(@RequestBody ClienteDTO cliente) {
        clienteService.inserir(cliente);
    }

    @PutMapping
    public ClienteDTO alterar(@RequestBody ClienteDTO cliente) {
        return clienteService.alterar(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Integer id) {
        try {
            if (clienteService.verificarComandaAtiva(id)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Não é possível excluir o cliente porque possui comanda ativa.");
            }

            clienteService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PutMapping("/alterar/{id}")
    public ResponseEntity<ClienteDTO> alterar(@PathVariable Integer id, @RequestBody ClienteDTO clienteAtualizado) {
        try {
            ClienteDTO clienteExistente = clienteService.buscarPorId(id);
            if (clienteExistente != null) {
                clienteAtualizado.setIdCliente(id);
                ClienteDTO clienteAlterado = clienteService.alterar(clienteAtualizado);
                return ResponseEntity.ok(clienteAlterado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("ultima-compra/{id}")
    public ClienteDTO atualizarUltimaCompra(@PathVariable Integer id, @RequestBody ClienteDTO clienteRequest) {
        return clienteService.atualizarUltimaCompra(id, clienteRequest.getUltimaCompraCliente());
    }

    @PutMapping("atualizar-saldo/{id}")
    public ResponseEntity<ClienteDTO> atualizarSaldo(@PathVariable Integer id, @RequestBody Map<String, Double> request) {
        Double novoSaldo = request.get("saldoCliente");
        ClienteDTO clienteAtualizado = clienteService.atualizarSaldo(id, novoSaldo);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @PutMapping("atualizar-fatura/{id}")
    public ResponseEntity<ClienteDTO> atualizarLimiteDisponivel(@PathVariable Integer id, @RequestBody Map<String, Double> request) {
        Double novaFatura = request.get("faturaCliente");
        ClienteDTO clienteAtualizado = clienteService.atualizarFaturaCliente(id, novaFatura);
        return ResponseEntity.ok(clienteAtualizado);
    }
}