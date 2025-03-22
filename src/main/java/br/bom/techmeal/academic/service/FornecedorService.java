package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Fornecedor;
import br.bom.techmeal.academic.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorDTO> listarTodos(){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream().map(FornecedorDTO::new).toList();
    }

    public void inserir(FornecedorDTO fornecedor){
        Fornecedor fornecedorEntity = new Fornecedor(fornecedor);
        fornecedorRepository.save(fornecedorEntity);
    }

    public FornecedorDTO alterar(FornecedorDTO fornecedor){
        Fornecedor fornecedorEntity = new Fornecedor(fornecedor);
        return new FornecedorDTO(fornecedorRepository.save(fornecedorEntity));
    }

    public void excluir(Integer id){
        Fornecedor fornecedor = fornecedorRepository.findById(id).get();
        fornecedorRepository.delete(fornecedor);
    }

    public FornecedorDTO buscarPorId(Integer id){
        return new FornecedorDTO(fornecedorRepository.findById(id).get());
    }
}
