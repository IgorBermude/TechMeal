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
}
