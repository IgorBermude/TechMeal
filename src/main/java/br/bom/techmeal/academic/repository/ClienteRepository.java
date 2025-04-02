package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByIdCliente(Integer id);

}
