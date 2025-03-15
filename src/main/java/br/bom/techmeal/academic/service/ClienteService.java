package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
    }

    public void inserir(ClienteDTO cliente){
        Cliente clienteEntity = new Cliente(cliente);
        clienteRepository.save(clienteEntity);
    }

    public ClienteDTO alterar(ClienteDTO cliente){
        Cliente clienteEntity = new Cliente(cliente);
        return new ClienteDTO(clienteRepository.save(clienteEntity));
    }

    public void excluir(Integer id){
        Cliente cliente = clienteRepository.findById(id).get();
        clienteRepository.delete(cliente);
    }

    public ClienteDTO buscarPorId(Integer id){
        return new ClienteDTO(clienteRepository.findById(id).get());
    }
}
