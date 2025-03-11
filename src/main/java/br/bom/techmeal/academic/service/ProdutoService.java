package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.dto.UsuarioDTO;
import br.bom.techmeal.academic.entity.Produto;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.repository.ProdutoRepository;
import br.bom.techmeal.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    public void inserir(ProdutoDTO produto){
        Produto produtoEntity = new Produto(produto);
        produtoRepository.save(produtoEntity);
    }

    public ProdutoDTO alterar(ProdutoDTO produto){
        Produto produtoEntity = new Produto(produto);
        return new ProdutoDTO(produtoRepository.save(produtoEntity));
    }

    public void excluir(Integer id){
        Produto produtoEntity = produtoRepository.findById(id).get();
        produtoRepository.delete(produtoEntity);
    }

    public ProdutoDTO buscarPorId(Integer id){
        return new ProdutoDTO(produtoRepository.findById(id).get());
    }
}
