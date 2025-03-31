package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    public List<ComandaDTO> listarTodos(){
        List<Comanda> comandas = comandaRepository.findAll();
        return comandas.stream().map(ComandaDTO::new).toList();
    }

    public void inserir(ComandaDTO comanda){
        Comanda comandaEntity = new Comanda(comanda);
        comandaRepository.save(comandaEntity);
    }

    public ComandaDTO alterar(ComandaDTO comanda){
        Comanda comandaEntity = new Comanda(comanda);
        return new ComandaDTO(comandaRepository.save(comandaEntity));
    }

    public void excluir(Integer id){
        Comanda comanda = comandaRepository.findById(id).get();
        comandaRepository.delete(comanda);
    }

    public ComandaDTO buscarPorId(Integer id){
        return new ComandaDTO(comandaRepository.findById(id).get());
    }
}
