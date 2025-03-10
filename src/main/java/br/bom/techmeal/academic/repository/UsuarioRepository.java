package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
// De acordo com o tipo id do Usuario int --> Integer
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByLogin(String login);

}
