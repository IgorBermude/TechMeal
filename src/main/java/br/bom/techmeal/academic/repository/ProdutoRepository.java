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
        value = "SELECT p.nome_produto, " +
                "SUM(cp.quantidade), " +
                "SUM(cp.quantidade * p.preco_produto) AS valor_venda, " +
                "SUM(cp.quantidade * p.valor_de_custo_produto) AS valor_custo, " +
                "SUM(cp.quantidade * (p.preco_produto - p.valor_de_custo_produto)) AS lucro " +
                "FROM comanda_produto cp " +
                "JOIN produto p ON cp.produto_id = p.id_produto " +
                "JOIN comanda c ON cp.comanda_id = c.id_compra_comanda " +
                "WHERE c.hora_entrada_comanda BETWEEN :dataInicio AND :dataFim " +
                "GROUP BY p.nome_produto " +
                "ORDER BY MIN(c.hora_entrada_comanda)",
        nativeQuery = true
    )
    List<Object[]> findVendasPorPeriodo(
        @Param("dataInicio") LocalDate dataInicio,
        @Param("dataFim") LocalDate dataFim
    );

    @Query(
            value = "SELECT COALESCE(SUM(cp.quantidade), 0) " +
                    "FROM comanda_produto cp " +
                    "JOIN comanda c ON cp.comanda_id = c.id_compra_comanda " +
                    "WHERE c.cliente_id_cliente = :clienteId " +
                    "AND c.hora_entrada_comanda BETWEEN :dataInicio AND :dataFim",
            nativeQuery = true
    )
    int somarProdutosCompradosPorClienteEPeriodo(
            @Param("clienteId") Integer clienteId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );
}
