package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Integer> {

    @Query("SELECT c FROM Comanda c WHERE c.cliente.id = :idCliente AND c.horaSaidaComanda IS NULL")
    Optional<Comanda> findComandaAtivaPorCliente(@Param("idCliente") int idCliente);


}
