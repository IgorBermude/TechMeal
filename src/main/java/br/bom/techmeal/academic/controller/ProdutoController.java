package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.service.ProdutoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Integer id) {
        ProdutoDTO produto = produtoService.buscarPorId(id); // Serviço que busca a conta pelo id

        if (produto != null) {
            return ResponseEntity.ok(produto); // Retorna a conta encontrada
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 caso não encontre
        }
    }

    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody ProdutoDTO produto) {
        try {
            // Gera o código de barras automaticamente
            String codigoBarras = produtoService.gerarCodigoDeBarras(produto.getNomeProduto());

            if (codigoBarras == null || codigoBarras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao gerar o código de barras.");
            }

            // Seta o código de barras no produto
            produto.setCodigoBarrasProduto(codigoBarras);

            // Salva o produto no banco de dados
            produtoService.inserir(produto);

            return ResponseEntity.ok("Produto cadastrado com sucesso! Código de barras: " + codigoBarras);
        } catch (DataIntegrityViolationException e) {
            // Log de erro
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erro: Código de barras já existe no sistema.");
        } catch (Exception e) {
            // Log de erro
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar o produto: " + e.getMessage());
        }
    }

    @PutMapping
    public ProdutoDTO alterar(@RequestBody ProdutoDTO produto) {
        return produtoService.alterar(produto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> alterar(@PathVariable Integer id, @RequestBody ProdutoDTO produtoAtualizado) {
        try {
            ProdutoDTO produtoExistente = produtoService.buscarPorId(id);

            if (produtoExistente != null) {
                // Aqui você pode adicionar qualquer validação extra que queira fazer para o produto
                // Exemplo: verificar se o código de barras já existe no sistema antes de atualizar

                // Atualiza o produto no serviço
                produtoAtualizado.setIdProduto(id); // Certifique-se de que o ID do produto a ser alterado é o correto
                ProdutoDTO produtoAlterado = produtoService.alterar(produtoAtualizado);

                return ResponseEntity.ok(produtoAlterado); // Retorna o produto atualizado
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Produto não encontrado
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se o produto não for encontrado
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        produtoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
