package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.TelaComPermissoesDTO;
import br.bom.techmeal.academic.repository.UsuarioPermissaoTelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioPermissaoTelaService {

    @Autowired
    private UsuarioPermissaoTelaRepository usuarioPermissaoTelaRepository;

    public List<TelaComPermissoesDTO> listarTelasComPermissoes(int idUsuario) {
        List<Object[]> resultados = usuarioPermissaoTelaRepository.listarPermissoesOrdenadasPorTela(idUsuario);

        Map<Integer, TelaComPermissoesDTO> mapaTelas = new LinkedHashMap<>();

        for (Object[] row : resultados) {
            Integer idTela = (Integer) row[0];
            String nomeTela = (String) row[1];
            String urlTela = (String) row[2];
            String acaoPermissao = (String) row[3];

            mapaTelas.computeIfAbsent(idTela, k -> new TelaComPermissoesDTO(nomeTela, urlTela, new HashSet<>()));
            mapaTelas.get(idTela).getPermissoes().add(acaoPermissao);
        }

        return new ArrayList<>(mapaTelas.values());
    }
}
