package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface HistoricoRecargaRepository extends JpaRepository<HistoricoRecarga, Integer> {

    @Query("SELECT h FROM HistoricoRecarga h WHERE h.cliente.idCliente = :clienteId AND h.dataRecargaHistoricoRecarga BETWEEN :inicio AND :fim")
    List<HistoricoRecarga> findByClienteAndDataRecargaBetween(
        @Param("clienteId") Integer clienteId,
        @Param("inicio") LocalDate inicio,
        @Param("fim") LocalDate fim
    );

    @Query("SELECT h FROM HistoricoRecarga h WHERE h.dataRecargaHistoricoRecarga = :dia")
    List<HistoricoRecarga> findByDataRecarga(@Param("dia") LocalDate dia);

    @Query("SELECT h FROM HistoricoRecarga h WHERE h.dataRecargaHistoricoRecarga < :data")
    List<HistoricoRecarga> findByDataRecargaBefore(@Param("data") LocalDate data);

    @Query("SELECT h FROM HistoricoRecarga h WHERE h.cliente.idCliente = :clienteId")
    List<HistoricoRecarga> findByCliente(@Param("clienteId") Integer clienteId);

}