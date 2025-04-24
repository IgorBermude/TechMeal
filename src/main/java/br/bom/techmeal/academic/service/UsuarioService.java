package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.UsuarioDTO;
import br.bom.techmeal.academic.entity.Permissao;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import br.bom.techmeal.academic.repository.PermissaoRepository;
import br.bom.techmeal.academic.repository.UsuarioPermissaoTelaRepository;
import br.bom.techmeal.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioPermissaoTelaRepository usuarioPermissaoTelaRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<UsuarioDTO> ListarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public Usuario inserir(UsuarioDTO usuario){
        Usuario usuarioEntity = new Usuario(usuario);
        usuarioEntity.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuarioEntity);

    } public UsuarioDTO alterarPorId(Integer id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        usuarioExistente.setNomeUsuario(usuarioDTO.getNomeUsuario());
        usuarioExistente.setLogin(usuarioDTO.getLogin());
        usuarioExistente.setTelefoneUsuario(usuarioDTO.getTelefoneUsuario());


        Usuario salvo = usuarioRepository.save(usuarioExistente);
        return new UsuarioDTO(salvo);
    }


    public UsuarioDTO alterar(UsuarioDTO usuario){
        Usuario usuarioEntity = new Usuario(usuario);
        usuarioEntity.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
    }



    public void excluir(Integer id){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }

    public Long buscarIdPorNomeUsuario(String nomeUsuario) {
        Usuario usuario = usuarioRepository.findByLogin(nomeUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return (long) usuario.getIdUsuario(); // Converte o int para Long
    }




    public UsuarioDTO buscarPorId(Integer id){
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }

}
