package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ControleContasRepository extends JpaRepository<ControleContas, Integer> {

    // Busca todas as contas com vencimento na data especificada e status "Não paga"
    @Query("SELECT c FROM ControleContas c WHERE c.dtVencimentoControleContas = :dtVencimento AND c.statusControleContas = :status")
    List<ControleContas> findByDtVencimentoControleContasAndStatusControleContas(@Param("dtVencimento") Date dtVencimento, @Param("status") String status);

}
