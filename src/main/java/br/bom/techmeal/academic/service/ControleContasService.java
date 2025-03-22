package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.entity.Fornecedor;
import br.bom.techmeal.academic.repository.ControleContasRepository;
import br.bom.techmeal.academic.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleContasService {

    @Autowired
    private ControleContasRepository controleContasRepository;

    public List<ControleContasDTO> listarTodos(){
        List<ControleContas> controleContas = controleContasRepository.findAll();
        return controleContas.stream().map(ControleContasDTO::new).toList();
    }

    public void inserir(ControleContasDTO controleContas){
        ControleContas controleContasEntity = new ControleContas(controleContas);
        controleContasRepository.save(controleContasEntity);
    }

    public ControleContasDTO alterar(ControleContasDTO controleContas){
        ControleContas controleContasEntity = new ControleContas(controleContas);
        return new ControleContasDTO(controleContasRepository.save(controleContasEntity));
    }

    public void excluir(Integer id){
        ControleContas controleContas = controleContasRepository.findById(id).get();
        controleContasRepository.delete(controleContas);
    }

    public ControleContasDTO buscarPorId(Integer id){
        return new ControleContasDTO(controleContasRepository.findById(id).get());
    }
}
