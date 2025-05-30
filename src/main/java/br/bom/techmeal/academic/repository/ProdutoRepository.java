package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    boolean existsByCodigoBarrasProduto(String codigoBarrasProduto);
    Produto findByCodigoBarrasProduto(String codigoBarrasProduto);

    @Query(
        value = "SELECT p.nome_produto, SUM(cp.quantidade), SUM(cp.quantidade * p.preco_produto) " +
                "FROM comanda_produto cp " +
                "JOIN produto p ON cp.produto_id = p.id_produto " +
                "JOIN comanda c ON cp.comanda_id = c.id_compra_comanda " +
                "WHERE c.hora_entrada_comanda BETWEEN :dataInicio AND :dataFim " +
                "GROUP BY p.nome_produto",
        nativeQuery = true
    )
    List<Object[]> findVendasPorPeriodo(
        @Param("dataInicio") LocalDate dataInicio,
        @Param("dataFim") LocalDate dataFim
    );
}
