package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Integer> {
    Tela findByNomeTela(String nomeTela);

}
