package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.dto.UsuarioDTO;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import br.bom.techmeal.academic.service.PermissaoService;
import br.bom.techmeal.academic.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping
    public List<UsuarioDTO> listarTodos(){
        return usuarioService.ListarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody UsuarioDTO usuario){
        usuarioService.inserir(usuario);
    }

    @PutMapping
    public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario){
        return usuarioService.alterar(usuario);
    }

    //http://endereco/usuario/3
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id){
        usuarioService.excluir(id);
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

    @DeleteMapping("/{idUsuario}/permissao/{idUsuarioPermissaoTela}")
    public ResponseEntity<Void> removerPermissao(
            @PathVariable int idUsuario,
            @PathVariable int idUsuarioPermissaoTela
    ) {
        permissaoService.removerPermissao(idUsuarioPermissaoTela);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idUsuario}/permissao")
    public ResponseEntity<List<UsuarioPermissaoTela>> listarPermissoes(@PathVariable int idUsuario) {
        return ResponseEntity.ok(permissaoService.listarPermissoesDoUsuario(idUsuario));
    }
}
