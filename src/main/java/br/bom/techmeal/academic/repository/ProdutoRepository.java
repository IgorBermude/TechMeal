package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
