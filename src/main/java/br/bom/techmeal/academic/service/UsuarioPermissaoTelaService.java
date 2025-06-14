package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.TelaComPermissoesDTO;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import br.bom.techmeal.academic.repository.UsuarioPermissaoTelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioPermissaoTelaService {

    @Autowired
    private UsuarioPermissaoTelaRepository usuarioPermissaoTelaRepository;


    public List<TelaComPermissoesDTO> listarTelasComPermissoes(int idUsuario) {
        List<UsuarioPermissaoTela> permissoes = usuarioPermissaoTelaRepository.findByUsuarioIdUsuario(idUsuario);

        // Mapa temporário com chave sendo o id da tela e valor um objeto com nome, url e set de permissões
        Map<Integer, TelaComPermissoesDTO> agrupado = new HashMap<>();

        for (UsuarioPermissaoTela upt : permissoes) {
            Integer idTela = upt.getTela().getIdTela();
            String nomeTela = upt.getTela().getNomeTela();
            String urlTela = upt.getTela().getUrlTela();
            String acao = upt.getPermissao().getAcaoPermissao();

            agrupado.compute(idTela, (k, v) -> {
                if (v == null) {
                    Set<String> permissoesSet = new HashSet<>();
                    permissoesSet.add(acao);
                    return new TelaComPermissoesDTO(idTela, nomeTela, urlTela, permissoesSet);
                } else {
                    v.getPermissoes().add(acao);
                    return v;
                }
            });
        }

        return new ArrayList<>(agrupado.values());
    }


}