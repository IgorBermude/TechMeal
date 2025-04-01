package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.service.ClienteService;
import br.bom.techmeal.academic.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<FornecedorDTO> listarTodos(){
        return fornecedorService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody FornecedorDTO fornecedor){
        fornecedorService.inserir(fornecedor);
    }

    @PutMapping
    public FornecedorDTO alterar(@RequestBody FornecedorDTO fornecedor){
        return fornecedorService.alterar(fornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        fornecedorService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> buscarFornecedorPorId(@PathVariable Integer id) {
        FornecedorDTO fornecedor = fornecedorService.buscarPorId(id); // Serviço que busca a conta pelo id

        if (fornecedor != null) {
            return ResponseEntity.ok(fornecedor); // Retorna a conta encontrada
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 caso não encontre
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<FornecedorDTO> alterar(@PathVariable Integer id, @RequestBody FornecedorDTO fornecedorAtualizado) {
        try {
            FornecedorDTO fornecedorExistente = fornecedorService.buscarPorId(id);

            if (fornecedorExistente != null) {
                // Aqui você pode adicionar qualquer validação extra que queira fazer para o produto
                // Exemplo: verificar se o código de barras já existe no sistema antes de atualizar

                // Atualiza o produto no serviço
                fornecedorAtualizado.setIdFornecedor(id); // Certifique-se de que o ID do produto a ser alterado é o correto
                FornecedorDTO fornecedorAlterado = fornecedorService.alterar(fornecedorAtualizado);

                return ResponseEntity.ok(fornecedorAlterado); // Retorna o produto atualizado
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Produto não encontrado
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se o produto não for encontrado
        }
    }
}
