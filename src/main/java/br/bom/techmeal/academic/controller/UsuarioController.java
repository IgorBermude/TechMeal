package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.UsuarioDTO;
import br.bom.techmeal.academic.dto.UsuarioPermissaoTelaDTO;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.repository.UsuarioPermissaoTelaRepository;
import br.bom.techmeal.academic.service.PermissaoService;
import br.bom.techmeal.academic.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private UsuarioPermissaoTelaRepository usuarioPermissaoTelaRepository;

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return usuarioService.ListarTodos();
    }

    @PostMapping
    public ResponseEntity<Usuario> inserir(@RequestBody UsuarioDTO usuarioDTO) {
        // Converte o DTO para a entidade Usuario (supondo que você tenha um método de conversão)
        Usuario usuario = usuarioService.inserir(usuarioDTO);

        // Retorna a resposta com o usuário recém-criado e o código HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> alterarPorId(
            @PathVariable Integer id,
            @RequestBody UsuarioDTO usuarioDTO
    ) {
        try {
            UsuarioDTO usuarioAtualizado = usuarioService.alterarPorId(id, usuarioDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PutMapping
    public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario) {
        return usuarioService.alterar(usuario);
    }

    // http://endereco/usuario/3
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id) {
        usuarioService.excluir(id);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @DeleteMapping("/tela/{idUsuario}")
    public ResponseEntity<?> deletarTelasDoUsuario(@PathVariable Long idUsuario) {
        usuarioPermissaoTelaRepository.deleteByUsuario_IdUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioDTO usuario = usuarioService.buscarPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{idUsuario}/permissao")
    public ResponseEntity<Void> adicionarPermissao(
            @PathVariable int idUsuario,
            @RequestParam int idTela,
            @RequestParam int idPermissao
    ) {
        permissaoService.atribuirPermissao(idUsuario, idTela, idPermissao);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/id/{nomeUsuario}")
    public ResponseEntity<Long> obterIdPorNomeUsuario(@PathVariable String nomeUsuario) {
        try {
            Long id = usuarioService.buscarIdPorNomeUsuario(nomeUsuario);
            return ResponseEntity.ok(id); // Retorna o ID do usuário como Long
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
