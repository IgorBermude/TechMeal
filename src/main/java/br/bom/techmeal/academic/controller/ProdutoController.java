package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProdutoDTO> listarTodos(){
        return produtoService.listarTodos();
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

            //Seta o codigo de barras no produto
            produto.setCodigoBarrasProduto(codigoBarras);

            // Salva o produto no banco de dados
            produtoService.inserir(produto);

            return ResponseEntity.ok("Produto cadastrado com sucesso! Código de barras: " + codigoBarras);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar o produto: " + e.getMessage());
        }
    }

    @PutMapping
    public ProdutoDTO alterar(@RequestBody ProdutoDTO produto){
        return produtoService.alterar(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        produtoService.excluir(id);
        return ResponseEntity.ok().build();
    }


}
