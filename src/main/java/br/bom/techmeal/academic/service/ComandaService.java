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

    public void inserir(ComandaDTO comanda) {
        comandaRepository.save(new Comanda(comanda));
    }

    public ComandaDTO atualizarProdutos(Integer id, List<Produto> produtos) {
        Comanda comanda = buscarComandaPorId(id);

        // Remover todos os produtos antigos da comanda
        comandaProdutoRepository.deleteByComanda(comanda);

        // Adiciona os novos produtos à comanda
        List<ComandaProduto> comandaProdutos = produtos.stream().map(produto ->
                new ComandaProduto(comanda, produto, 1) // Define a quantidade como 1
        ).toList();

        // Salva os novos produtos relacionados à comanda
        comandaProdutoRepository.saveAll(comandaProdutos);

        return new ComandaDTO(comanda);
    }

    public ComandaDTO alterar(ComandaDTO comanda) {
        return new ComandaDTO(comandaRepository.save(new Comanda(comanda)));
    }

    public void excluir(Integer id) {
        Comanda comanda = buscarComandaPorId(id);
        comandaRepository.delete(comanda);
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO) {
        Cliente cliente = comandaDTO.getCliente();
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inválido");
        }

        Integer idCliente = cliente.getIdCliente();
        comandaRepository.findComandaAtivaPorCliente(idCliente)
                .ifPresent(c -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma comanda aberta para este cliente");
                });

        Cliente clienteBD = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Comanda novaComanda = new Comanda();
        novaComanda.setCliente(clienteBD);
        novaComanda.setHoraEntradaComanda(new Date());
        comandaRepository.save(novaComanda);

        return new ComandaDTO(novaComanda);
    }

    public ComandaDTO buscarPorId(Integer id) {
        return new ComandaDTO(buscarComandaPorId(id));
    }

    public ComandaDTO atualizarComanda(Integer id, ComandaDTO comandaDTO) {
        Comanda comanda = buscarComandaPorId(id);
        comanda.setValorTotalComanda(comandaDTO.getValorTotalComanda());


        // Remover os produtos antigos da comanda
        comandaProdutoRepository.deleteByComanda(comanda);

        // Buscar os novos produtos pelo ID corretamente
        List<Produto> produtosAtualizados = produtoRepository.findAllById(
                comandaDTO.getProdutoListComanda().stream()
                        .map(ProdutoQuantidadeDTO::getIdProduto) // Correção aqui
                        .toList()
        );

        // Criar novas relações ComandaProduto
        List<ComandaProduto> comandaProdutos = produtosAtualizados.stream()
                .map(produto -> new ComandaProduto(comanda, produto, 1)) // Sempre quantidade 1
                .collect(Collectors.toList());

        // Salvar os produtos na tabela comanda_produto
        comandaProdutoRepository.saveAll(comandaProdutos);

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
