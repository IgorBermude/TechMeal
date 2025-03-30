package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByIdCliente(Integer id);

}
