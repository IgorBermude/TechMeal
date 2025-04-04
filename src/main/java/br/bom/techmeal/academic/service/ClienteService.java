package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
    }

    public ClienteDTO buscarPorId(Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isEmpty()) {
            return null; // Você pode lançar uma exceção personalizada aqui, se desejar
        }

        return new ClienteDTO(clienteOpt.get());
    }

    public void inserir(ClienteDTO cliente) {
        Cliente clienteEntity = new Cliente(cliente);
        clienteRepository.save(clienteEntity);
    }

    public ClienteDTO alterar(ClienteDTO cliente) {
        Cliente clienteEntity = new Cliente(cliente);
        return new ClienteDTO(clienteRepository.save(clienteEntity));
    }

    public void excluir(Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        clienteOpt.ifPresent(clienteRepository::delete);
    }

    // Método para atualizar apenas o saldoCliente
    public ClienteDTO atualizarSaldo(Integer id, double novoSaldo) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setSaldoCliente(novoSaldo);
            clienteRepository.save(cliente);
            return new ClienteDTO(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    // Método para atualizar apenas a faturaCliente
    public ClienteDTO atualizarFaturaCliente(Integer id, double novaFatura) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setFaturaCliente(novaFatura); // Certifique-se de que esse atributo existe na entidade Cliente
            clienteRepository.save(cliente);
            return new ClienteDTO(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }
}
