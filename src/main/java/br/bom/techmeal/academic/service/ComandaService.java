package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.dto.ProdutoQuantidadeDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.ComandaProduto;
import br.bom.techmeal.academic.entity.Produto;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.ComandaProdutoRepository;
import br.bom.techmeal.academic.repository.ComandaRepository;
import br.bom.techmeal.academic.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ComandaProdutoRepository comandaProdutoRepository;

    public ComandaService(ComandaRepository comandaRepository, ClienteRepository clienteRepository,
                          ProdutoRepository produtoRepository, ComandaProdutoRepository comandaProdutoRepository) {
        this.comandaRepository = comandaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.comandaProdutoRepository = comandaProdutoRepository;
    }

    public List<ComandaDTO> listarTodos() {
        return comandaRepository.findAll().stream().map(ComandaDTO::new).toList();
    }

    public void inserir(ComandaDTO comandaDTO) {
        Comanda comanda = new Comanda(comandaDTO);
        comandaRepository.save(comanda);
    }

    public ComandaDTO alterar(ComandaDTO comandaDTO) {
        Comanda comanda = buscarComandaPorId(comandaDTO.getIdCompraComanda());
        comanda.setValorTotalComanda(comandaDTO.getValorTotalComanda());
        return new ComandaDTO(comandaRepository.save(comanda));
    }

    public void excluir(Integer id) {
        Comanda comanda = buscarComandaPorId(id);
        comandaRepository.delete(comanda);
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO) {
        Cliente cliente = clienteRepository.findById(comandaDTO.getCliente().getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        // Verifica se já existe uma comanda aberta para o cliente
        comandaRepository.findComandaAtivaPorCliente(cliente.getIdCliente())
                .ifPresent(c -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma comanda aberta para este cliente");
                });

        Comanda novaComanda = new Comanda();
        novaComanda.setCliente(cliente);
        novaComanda.setHoraEntradaComanda(new Date());
        comandaRepository.save(novaComanda);

        return new ComandaDTO(novaComanda);
    }

    public ComandaDTO buscarPorId(Integer id) {
        return new ComandaDTO(buscarComandaPorId(id));
    }

    public ComandaDTO atualizarProdutos(Integer id, List<ProdutoQuantidadeDTO> produtosDTO) {
        Comanda comanda = buscarComandaPorId(id);

        // Remover todos os produtos antigos da comanda antes de adicionar os novos
        comandaProdutoRepository.deleteByComanda(comanda);

        // Adiciona os novos produtos à comanda
        List<ComandaProduto> comandaProdutos = produtosDTO.stream().map(produtoDTO -> {
            Produto produto = produtoRepository.findById(produtoDTO.getIdProduto())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            return new ComandaProduto(comanda, produto, produtoDTO.getQuantidade());
        }).toList();

        // Salva os novos produtos relacionados à comanda
        comandaProdutoRepository.saveAll(comandaProdutos);

        comanda.setComandaProdutos(comandaProdutos);
        comandaRepository.save(comanda);

        return new ComandaDTO(comanda);
    }

    public ComandaDTO atualizarComanda(Integer id, ComandaDTO comandaDTO) {
        Comanda comanda = buscarComandaPorId(id);
        comanda.setValorTotalComanda(comandaDTO.getValorTotalComanda());

        // Remove os produtos antigos da comanda antes de adicionar os novos
        comanda.getComandaProdutos().clear();

        // Adiciona os novos produtos à comanda
        List<ComandaProduto> comandaProdutos = comandaDTO.getComandaProdutos().stream()
                .map(cpDTO -> {
                    Produto produto = produtoRepository.findById(cpDTO.getIdProduto())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
                    return new ComandaProduto(comanda, produto, cpDTO.getQuantidade());
                })
                .collect(Collectors.toList());

        // Adiciona os produtos à comanda e salva
        comanda.getComandaProdutos().addAll(comandaProdutos);
        comandaRepository.save(comanda);

        return new ComandaDTO(comanda);
    }



    public ComandaDTO atualizarHoraSaida(Integer id, Date horaSaida) {
        Comanda comanda = buscarComandaPorId(id);
        comanda.setHoraSaidaComanda(horaSaida);
        comandaRepository.save(comanda);
        return new ComandaDTO(comanda);
    }

    public ComandaDTO buscarUltimaComandaAtiva(Integer clienteId) {
        Comanda comanda = comandaRepository.findComandaAtivaPorCliente(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma comanda ativa encontrada para este cliente."));
        return new ComandaDTO(comanda);
    }

    private Comanda buscarComandaPorId(Integer id) {
        return comandaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comanda não encontrada"));
    }
}
