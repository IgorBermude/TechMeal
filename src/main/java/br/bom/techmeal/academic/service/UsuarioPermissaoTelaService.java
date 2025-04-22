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

        Map<String, Set<String>> agrupado = new HashMap<>();

        for (UsuarioPermissaoTela upt : permissoes) {
            String nomeTela = upt.getTela().getNomeTela();
            String acao = upt.getPermissao().getAcaoPermissao();

            agrupado
                    .computeIfAbsent(nomeTela, k -> new HashSet<>())
                    .add(acao);
        }

        return agrupado.entrySet().stream()
                .map(entry -> new TelaComPermissoesDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

}
