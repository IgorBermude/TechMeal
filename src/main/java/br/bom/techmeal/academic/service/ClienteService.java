package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
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

    public void excluir(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        clienteRepository.delete(cliente);
    }

    public ClienteDTO atualizarSaldo(Integer id, double novoSaldo) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        cliente.setSaldoCliente(novoSaldo);
        clienteRepository.save(cliente);
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


}
