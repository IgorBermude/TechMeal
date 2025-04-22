package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
    Permissao findByAcaoPermissao(String acaoPermissao);


}

