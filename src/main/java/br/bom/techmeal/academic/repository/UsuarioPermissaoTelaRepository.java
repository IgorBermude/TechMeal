package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioPermissaoTelaRepository extends JpaRepository<UsuarioPermissaoTela, Integer> {

    @Query(value = """
        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
        FROM usuario_permissao_tela upt
        JOIN usuario u ON u.id_usuario = upt.usuario_id
        JOIN tela t ON t.id_tela = upt.tela_id
        JOIN permissao p ON p.id_permissao = upt.permissao_id
        WHERE u.id_usuario = :idUsuario
          AND t.nome_tela = :nomeTela
          AND p.acao_permissao = :acaoPermissao
    """, nativeQuery = true)
    boolean existsPermissao(@Param("idUsuario") int idUsuario,
                            @Param("nomeTela") String nomeTela,
                            @Param("acaoPermissao") String acaoPermissao);

    Optional<UsuarioPermissaoTela> findByUsuario_IdUsuarioAndPermissao_IdPermisaoAndTela_IdTela(int idUsuario, int idPermissao, int idTela);

    List<UsuarioPermissaoTela> findByUsuarioIdUsuario(int idUsuario);

    void deleteByUsuario_IdUsuario(Long idUsuario);

    @Modifying
    @Transactional
    void deleteByTela_IdTela(Integer idTela);

    // NOVA QUERY PARA LISTAR PERMISSÕES ORDENADAS PELA ORDEM DAS TELAS
    @Query(value = """
    SELECT t.id_tela, t.nome_tela, t.url_tela, p.acao_permissao
    FROM usuario_permissao_tela upt
    JOIN tela t ON t.id_tela = upt.tela_id_tela  
    JOIN permissao p ON permissao_id_permisao = upt.permissao_id_permisao  
    WHERE upt.usuario_id_usuario = :idUsuario
    ORDER BY t.id_tela ASC
""", nativeQuery = true)
    List<Object[]> listarPermissoesOrdenadasPorTela(@Param("idUsuario") int idUsuario);








}
