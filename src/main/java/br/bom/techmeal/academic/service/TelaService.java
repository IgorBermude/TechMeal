package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.dto.TelaDTO;
import br.bom.techmeal.academic.entity.Fornecedor;
import br.bom.techmeal.academic.entity.Tela;
import br.bom.techmeal.academic.repository.FornecedorRepository;
import br.bom.techmeal.academic.repository.TelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelaService {
    @Autowired
    private TelaRepository telaRepository;

    public List<TelaDTO> listarTodos(){
        List<Tela> telas = telaRepository.findAll();
        return telas.stream().map(TelaDTO::new).toList();
    }

    public void inserir(TelaDTO tela){
        Tela telaEntity = new Tela(tela);
        telaRepository.save(telaEntity);
    }

    public TelaDTO alterar(TelaDTO tela){
        Tela telaEntity = new Tela(tela);
        return new TelaDTO(telaRepository.save(telaEntity));
    }

    public void excluir(Integer id){
        Tela tela = telaRepository.findById(id).get();
        telaRepository.delete(tela);
    }

    public TelaDTO buscarPorId(Integer id){
        return new TelaDTO(telaRepository.findById(id).get());
    }

}
