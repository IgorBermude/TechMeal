package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.dto.HistoricoRecargaDTO;
import br.bom.techmeal.academic.entity.Fornecedor;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import br.bom.techmeal.academic.repository.FornecedorRepository;
import br.bom.techmeal.academic.repository.HistoricoRecargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoRecargaService {

    @Autowired
    private HistoricoRecargaRepository historicoRecargaRepository;

    public List<HistoricoRecargaDTO> listarTodos(){
        List<HistoricoRecarga> historicoRecargas = historicoRecargaRepository.findAll();
        return historicoRecargas.stream().map(HistoricoRecargaDTO::new).toList();
    }

    public void inserir(HistoricoRecargaDTO historicoRecarga){
        HistoricoRecarga historicoRecargaEntity = new HistoricoRecarga(historicoRecarga);
        historicoRecargaRepository.save(historicoRecargaEntity);
    }

    public HistoricoRecargaDTO alterar(HistoricoRecargaDTO historicoRecarga){
        HistoricoRecarga historicoRecargaEntity = new HistoricoRecarga(historicoRecarga);
        return new HistoricoRecargaDTO(historicoRecargaRepository.save(historicoRecargaEntity));
    }

    public void excluir(Integer id){
        HistoricoRecarga historicoRecarga = historicoRecargaRepository.findById(id).get();
        historicoRecargaRepository.delete(historicoRecarga);
    }

    public HistoricoRecargaDTO buscarPorId(Integer id){
        return new HistoricoRecargaDTO(historicoRecargaRepository.findById(id).get());
    }

}
