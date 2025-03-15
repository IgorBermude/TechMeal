package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
