package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Integer> {

    @Query("SELECT c FROM Comanda c WHERE c.cliente.id = :idCliente AND c.horaSaidaComanda IS NULL")
    Optional<Comanda> findComandaAtivaPorCliente(@Param("idCliente") int idCliente);

    @Query("SELECT c FROM Comanda c WHERE c.cliente.id = :idCliente AND c.horaSaidaComanda IS NOT NULL ORDER BY c.horaSaidaComanda DESC")
    List<Comanda> findUltimaComandaFinalizada(@Param("idCliente") int idCliente, Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE FROM Comanda c WHERE c.cliente.id = :idCliente")
    void deleteByClienteId(@Param("idCliente") int idCliente);

    @Transactional
    void deleteByCliente_IdCliente(int clienteId);

    List<Comanda> findByCliente(Cliente cliente);

    @Query("SELECT c FROM Comanda c WHERE c.cliente = :cliente AND c.horaEntradaComanda BETWEEN :dataInicio AND :dataFim")
    List<Comanda> findByClienteAndHoraEntradaComandaBetween(
        @Param("cliente") Cliente cliente,
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim
    );
}