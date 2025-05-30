package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.ComandaProduto;
import br.bom.techmeal.academic.entity.ComandaProdutoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComandaProdutoRepository extends JpaRepository<ComandaProduto, ComandaProdutoID> {
    void deleteByComanda(Comanda comanda);

}

