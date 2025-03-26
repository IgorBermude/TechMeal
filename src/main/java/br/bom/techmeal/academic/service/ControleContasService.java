package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.repository.ControleContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.sql.Date; // Use java.sql.Date para compatibilidade com o banco de dados

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



    public void atualizarContasVencidas() {
        // Cria um objeto java.util.Date para a data atual
        java.util.Date hojeUtil = new java.util.Date();

        // Converte para java.sql.Date, mas garantindo que a hora será zero
        java.sql.Date hojeSql = new java.sql.Date(hojeUtil.getTime());  // Pega o tempo em milissegundos

        // Buscar contas vencendo hoje e ainda não pagas
        List<ControleContas> contas = controleContasRepository
                .findByDtVencimentoControleContasAndStatusControleContas(hojeSql, "Não paga");

        // Atualizar o status para "Vencida"
        for (ControleContas conta : contas) {
            conta.setStatusControleContas("Vencida");
        }

        // Salvar atualizações
        controleContasRepository.saveAll(contas);
    }




}
