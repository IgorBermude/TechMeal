package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.ComandaProdutoRepository;
import br.bom.techmeal.academic.repository.ComandaRepository;
import br.bom.techmeal.academic.repository.HistoricoRecargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private HistoricoRecargaRepository historicoRecargaRepository;

    @Autowired
    private ComandaProdutoRepository comandaProdutoRepository;


    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
    }

    public Cliente buscarClientePorCartao(String idCartaoCliente) {
        return clienteRepository.findByIdCartaoCliente(idCartaoCliente); // Método no repositório
    }

    public ClienteDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        return new ClienteDTO(cliente);
    }

    public void inserir(ClienteDTO clienteDTO) {
        Cliente clienteEntity = new Cliente(clienteDTO);
        clienteRepository.save(clienteEntity);
    }

    public ClienteDTO alterar(ClienteDTO clienteDTO) {
        Cliente clienteEntity = new Cliente(clienteDTO);
        return new ClienteDTO(clienteRepository.save(clienteEntity));
    }

    @Transactional
    public void excluir(int clienteId) {
        // 1) Deletar registros em comanda_produto relacionados ao cliente
        comandaProdutoRepository.deleteByComanda_Cliente_IdCliente(clienteId);

        // 2) Deletar as comandas do cliente
        comandaRepository.deleteByCliente_IdCliente(clienteId);

        // 3) Deletar o histórico de recargas do cliente
        historicoRecargaRepository.deleteByCliente_IdCliente(clienteId);

        // 3) Deletar o cliente
        clienteRepository.deleteById(clienteId);
    }


    public ClienteDTO atualizarSaldo(Integer id, double novoSaldo) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        double saldoAntigo = cliente.getSaldoCliente();
        double valorRecarga = novoSaldo - saldoAntigo;

        cliente.setSaldoCliente(novoSaldo);
        clienteRepository.save(cliente);

        // Se houve recarga (saldo aumentou), salva no histórico
        if (valorRecarga > 0) {
            HistoricoRecarga historico = new HistoricoRecarga();
            historico.setCliente(cliente);
            historico.setValorRecargaHistoricoRecarga(valorRecarga);
            historico.setDataRecargaHistoricoRecarga(new Date());
            historicoRecargaRepository.save(historico);
        }

        return new ClienteDTO(cliente);
    }

    public ClienteDTO atualizarFaturaCliente(Integer id, double novaFatura) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        cliente.setFaturaCliente(novaFatura);
        clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    public ClienteDTO atualizarUltimaCompra(Integer id, Date dataEntradaComanda) {
        if (dataEntradaComanda == null) {
            throw new IllegalArgumentException("A data da última compra não pode ser nula.");
        }

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        cliente.setUltimaCompraCliente(dataEntradaComanda);
        clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    public boolean verificarComandaAtiva(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        return comandaRepository.findComandaAtivaPorCliente(cliente.getIdCliente()).isPresent();
    }

    @Transactional
    public void limparComandas(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        comandaRepository.deleteByClienteId(cliente.getIdCliente());
    }

    public boolean clientePossuiRegistroEmComandaProduto(int clienteId) {
        return comandaProdutoRepository.existsByComanda_Cliente_IdCliente(clienteId);
    }

}
