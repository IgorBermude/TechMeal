package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public void inserir(@RequestBody ProdutoDTO produto){
        produtoService.inserir(produto);
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

    @GetMapping("/gerar-codigo")
    public ResponseEntity<String> gerarCodigoDeBarras() throws IOException {
        try {
            // Chama o serviço para gerar o código de barras, passando o ID
            String codigoBarras = produtoService.gerarCodigoDeBarras();

            // Verifica se o código de barras foi gerado com sucesso
            if (codigoBarras == null || codigoBarras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao gerar o código de barras.");
            }

            // Retorna a resposta com o código de barras gerado
            return ResponseEntity.ok("Código de barras gerado com sucesso: " + codigoBarras);
        } catch (IOException e) {
            // Log do erro (opcional)
            e.printStackTrace();

            // Retorna uma resposta de erro em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a solicitação: " + e.getMessage());
        }
    }


}
