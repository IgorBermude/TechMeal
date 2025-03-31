package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {

}
