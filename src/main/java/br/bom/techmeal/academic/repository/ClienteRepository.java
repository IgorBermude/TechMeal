package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByIdCliente(Integer id);
    Cliente findByIdCartaoCliente(String idCartaoCliente);

    @Query(
        value = "SELECT * FROM cliente c WHERE EXTRACT(DAY FROM c.dt_nasc_cliente) = :dia AND EXTRACT(MONTH FROM c.dt_nasc_cliente) = :mes",
        nativeQuery = true
    )
    List<Cliente> findByDiaMesAniversario(@Param("dia") int dia, @Param("mes") int mes);

    List<Cliente> findByFaturaClienteGreaterThan(double valor);
}
