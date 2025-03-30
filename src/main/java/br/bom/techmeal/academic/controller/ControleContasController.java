package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.service.ControleContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Control;
import java.util.List;

@RestController
@RequestMapping(value = "/controlecontas")
public class ControleContasController {

    @Autowired
    private ControleContasService controleContasService;


    @GetMapping
    public List<ControleContasDTO> listarTodos() {
        controleContasService.atualizarContasVencidas(); // Atualiza as contas vencidas
        return controleContasService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ControleContasDTO> buscarContaPorId(@PathVariable Integer id) {
        ControleContasDTO conta = controleContasService.buscarPorId(id); // Serviço que busca a conta pelo id

        if (conta != null) {
            return ResponseEntity.ok(conta); // Retorna a conta encontrada
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 caso não encontre
        }
    }


    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody ControleContasDTO controleContas) {
        controleContasService.inserir(controleContas);
        return ResponseEntity.status(201).build();  // Retorna 201 Created
    }


    @PutMapping
    public ResponseEntity<ControleContasDTO> alterar(@RequestBody ControleContasDTO controleContas) {
        ControleContasDTO updatedConta = controleContasService.alterar(controleContas);
        return ResponseEntity.ok(updatedConta); // Retorna a conta atualizada com 200 OK
    }


    @PutMapping("/pagar/{id}")
    public ResponseEntity<ControleContasDTO> pagarConta(@PathVariable Integer id) {
        try {
            ControleContasDTO contaPaga = controleContasService.pagarConta(id);
            System.out.println("Conta paga com sucesso: " + contaPaga);
            return ResponseEntity.status(201).build(); // Retorna a conta paga com 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se a conta não for encontrada
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ControleContasDTO> alterar(@PathVariable Integer id, @RequestBody ControleContasDTO controleContasAtualizado){
        try {
            ControleContasDTO controleContasExistente = controleContasService.buscarPorId(id);

            if (controleContasExistente != null) {
                // Aqui você pode adicionar qualquer validação extra que queira fazer para o produto
                // Exemplo: verificar se o código de barras já existe no sistema antes de atualizar

                // Atualiza o produto no serviço
                controleContasAtualizado.setIdContaControleContas(id); // Certifique-se de que o ID do produto a ser alterado é o correto
                ControleContasDTO controleContasAlterado = controleContasService.alterar(id, controleContasAtualizado);

                return ResponseEntity.ok(controleContasAlterado); // Retorna o produto atualizado
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Produto não encontrado
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se o produto não for encontrado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        try {
            controleContasService.excluir(id);
            return ResponseEntity.ok().build(); // Retorna 200 OK se a exclusão for bem-sucedida
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se a conta não for encontrada
        }
    }
}
