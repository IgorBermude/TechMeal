package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.UsuarioDTO;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> ListarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public void inserir(UsuarioDTO usuario){
        Usuario usuarioEntity = new Usuario(usuario);
        usuarioEntity.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        usuarioRepository.save(usuarioEntity);
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

    public UsuarioDTO buscarPorId(Integer id){
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }

}
