package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.UsuarioPermissaoTelaDTO;
import br.bom.techmeal.academic.entity.Permissao;
import br.bom.techmeal.academic.entity.Tela;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import br.bom.techmeal.academic.repository.PermissaoRepository;
import br.bom.techmeal.academic.repository.TelaRepository;
import br.bom.techmeal.academic.repository.UsuarioPermissaoTelaRepository;
import br.bom.techmeal.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissaoService {

    @Autowired
    private UsuarioPermissaoTelaRepository usuarioPermissaoTelaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TelaRepository telaRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Long buscarIdPorNomeUsuario(String login) {
        return usuarioService.buscarIdPorNomeUsuario(login);
    }

    public boolean temPermissao(int idUsuario, String nomeTela, String acaoPermissao) {
        return usuarioPermissaoTelaRepository.existsPermissao(idUsuario, nomeTela, acaoPermissao);
    }

    public void atribuirPermissao(int idUsuario, int idTela, int idPermissao) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow();
        Tela tela = telaRepository.findById(idTela).orElseThrow();
        Permissao permissao = permissaoRepository.findById(idPermissao).orElseThrow();

        UsuarioPermissaoTela upt = new UsuarioPermissaoTela();
        upt.setUsuario(usuario);
        upt.setTela(tela);
        upt.setPermissao(permissao);

        usuarioPermissaoTelaRepository.save(upt);
    }

    public void removerPermissao(int idUsuarioPermissaoTela) {
        usuarioPermissaoTelaRepository.deleteById(idUsuarioPermissaoTela);
    }

    // Método atualizado para retornar uma lista de DTOs
    public List<UsuarioPermissaoTelaDTO> listarPermissoesDoUsuario(int idUsuario) {
        List<UsuarioPermissaoTela> permissoes = usuarioPermissaoTelaRepository.findByUsuarioIdUsuario(idUsuario);

        // Mapeia a lista de entidades para DTOs
        return permissoes.stream()
                .map(UsuarioPermissaoTelaDTO::new) // Mapeamento de entidade para DTO
                .collect(Collectors.toList());
    }

    public List<Permissao> listarTodasPermissoes() {
        return permissaoRepository.findAll();
    }

    public Permissao salvarPermissao(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public void excluirPermissao(int idPermissao) {
        permissaoRepository.deleteById(idPermissao);
    }
}
