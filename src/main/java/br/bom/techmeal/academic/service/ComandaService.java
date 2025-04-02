package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.Produto;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Date;
import java.util.List;


@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ComandaDTO> listarTodos() {
        List<Comanda> comandas = comandaRepository.findAll();
        return comandas.stream().map(ComandaDTO::new).toList();
    }

    public void inserir(ComandaDTO comanda) {
        Comanda comandaEntity = new Comanda(comanda);
        comandaRepository.save(comandaEntity);
    }

    public ComandaDTO alterar(ComandaDTO comanda) {
        Comanda comandaEntity = new Comanda(comanda);
        return new ComandaDTO(comandaRepository.save(comandaEntity));
    }

    public void excluir(Integer id) {
        Comanda comanda = comandaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
        comandaRepository.delete(comanda);
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO) {
        Cliente cliente = comandaDTO.getCliente();

        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inválido");
        }

        Integer idCliente = cliente.getIdCliente();

        if (comandaRepository.findComandaAtivaPorCliente(idCliente).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma comanda aberta para este cliente");
        }

        Cliente clienteBD = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Comanda novaComanda = new Comanda();
        novaComanda.setCliente(clienteBD);
        novaComanda.setHoraEntradaComanda(new Date());

        comandaRepository.save(novaComanda);

        return new ComandaDTO(novaComanda);
    }

    public ComandaDTO buscarPorId(Integer id) {
        return new ComandaDTO(comandaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada")));
    }

    public ComandaDTO atualizarComanda(Integer clienteId, List<Produto> produtos) {
        Comanda comanda = comandaRepository.findComandaAtivaPorCliente(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma comanda aberta encontrada para o cliente"));

        double valorTotal = produtos.stream().mapToDouble(Produto::getPrecoProduto).sum();
        comanda.setProdutoListComanda(produtos);
        comanda.setValorTotalComanda(valorTotal);



        return new ComandaDTO(comandaRepository.save(comanda));
    }

    public ComandaDTO atualizarHoraSaida(Integer id, Date horaSaida) {
        Comanda comanda = comandaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));

        comanda.setHoraSaidaComanda(horaSaida);
        comandaRepository.save(comanda);

        return new ComandaDTO(comanda);
    }

    public ComandaDTO buscarUltimaComandaAtiva(Integer clienteId) {
        Comanda comanda = comandaRepository.findComandaAtivaPorCliente(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma comanda ativa encontrada para este cliente."));
        return new ComandaDTO(comanda);
    }


}