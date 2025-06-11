package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ControleContasRepository extends JpaRepository<ControleContas, Integer> {

    // Busca contas com status "Não Paga" e vencidas
    @Query("SELECT c FROM ControleContas c WHERE c.statusControleContas = 'Não Paga' AND c.dtVencimentoControleContas <= :currentDate")
    List<ControleContas> findVencidas(@Param("currentDate") Date currentDate);

    // Buscar contas pagas na data específica (considerando fornecedores)
    @Query("SELECT c FROM ControleContas c WHERE FUNCTION('DATE', c.dtVencimentoControleContas) = :data")
    List<ControleContas> findByDataPagamento(@Param("data") LocalDate data);

    // Buscar contas pagas antes de uma data específica
    @Query("SELECT c FROM ControleContas c WHERE FUNCTION('DATE', c.dtVencimentoControleContas) < :data")
    List<ControleContas> findByDataPagamentoBefore(@Param("data") LocalDate data);

    //   buscar uma conta pelo ID
    ControleContas findByIdContaControleContas(Integer id);

    //   atualizar o status de uma conta para "Paga"
    @Query("UPDATE ControleContas c SET c.statusControleContas = 'Paga' WHERE c.idContaControleContas = :id")
    void pagarConta(@Param("id") Integer id);
}