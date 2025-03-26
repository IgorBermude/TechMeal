package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.repository.ControleContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ControleContasService {

    @Autowired
    private ControleContasRepository controleContasRepository;

    public List<ControleContasDTO> listarTodos() {
        atualizarContasVencidas(); // Atualiza contas vencidas antes de listar
        List<ControleContas> controleContas = controleContasRepository.findAll();
        return controleContas.stream().map(ControleContasDTO::new).toList();
    }

    // Método para atualizar o status das contas vencidas
    public void atualizarContasVencidas() {
        Date currentDate = new Date(); // Data atual
        List<ControleContas> contasVencidas = controleContasRepository.findVencidas(currentDate);

        for (ControleContas conta : contasVencidas) {
            conta.setStatusControleContas("Vencida");
            controleContasRepository.save(conta); // Atualiza a conta no banco
        }
    }

    public void inserir(ControleContasDTO controleContas) {
        ControleContas controleContasEntity = new ControleContas(controleContas);
        controleContasRepository.save(controleContasEntity);
    }

    public ControleContasDTO alterar(ControleContasDTO controleContas) {
        ControleContas controleContasEntity = new ControleContas(controleContas);
        return new ControleContasDTO(controleContasRepository.save(controleContasEntity));
    }

    public void excluir(Integer id) {
        ControleContas controleContas = controleContasRepository.findById(id).orElseThrow();
        controleContasRepository.delete(controleContas);
    }

    public ControleContasDTO buscarPorId(Integer id) {
        return new ControleContasDTO(controleContasRepository.findById(id).orElseThrow());
    }
}
