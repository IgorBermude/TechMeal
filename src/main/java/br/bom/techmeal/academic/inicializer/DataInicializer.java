package br.bom.techmeal.academic.inicializer;

import br.bom.techmeal.academic.entity.Permissao;
import br.bom.techmeal.academic.entity.Tela;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import br.bom.techmeal.academic.repository.PermissaoRepository;
import br.bom.techmeal.academic.repository.TelaRepository;
import br.bom.techmeal.academic.repository.UsuarioRepository;
import br.bom.techmeal.academic.repository.UsuarioPermissaoTelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInicializer implements CommandLineRunner {

    @Autowired
    private TelaRepository telaRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioPermissaoTelaRepository usuarioPermissaoTelaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (telaRepository.count() == 0) {
            System.out.println("-> Inserindo Telas...");
            telaRepository.save(new Tela("Tela de Dashboard", "/home"));
            telaRepository.save(new Tela("Tela de Produtos", "/produtos"));
            telaRepository.save(new Tela("Tela de Fornecedores", "/fornecedores"));
            telaRepository.save(new Tela("Tela de Clientes", "/clientes"));
            telaRepository.save(new Tela("Tela de Recarga", "/recarga"));
            telaRepository.save(new Tela("Tela de Vendas", "/vendas"));
            telaRepository.save(new Tela("Tela de Pagamentos", "/pagamentos"));
            telaRepository.save(new Tela("Tela de Relatórios", "/relatorios"));
            telaRepository.save(new Tela("Tela de Entrada", "/entrada"));
            telaRepository.save(new Tela("Tela de Saída", "/saida"));
            telaRepository.save(new Tela("Tela Usuarios", "/usuarios"));
        } else {
            System.out.println("-> Telas já existem");
        }

        if (permissaoRepository.count() == 0) {
            System.out.println("-> Inserindo Permissões...");
            permissaoRepository.save(new Permissao("Incluir", "POST"));
            permissaoRepository.save(new Permissao("Alterar", "PUT"));
            permissaoRepository.save(new Permissao("Excluir", "DELETE"));
            permissaoRepository.save(new Permissao("Visualizar", "GET"));
        } else {
            System.out.println("-> Permissões já existem");
        }

        if (usuarioRepository.count() == 0) {
            System.out.println("-> Criando usuário João...");
            Usuario usuario = new Usuario();
            usuario.setNomeUsuario("João");
            usuario.setSenhaUsuario("senha123");
            usuarioRepository.save(usuario);

            Permissao permissaoVisualizar = permissaoRepository.findByAcaoPermissao("Visualizar");

            for (Tela tela : telaRepository.findAll()) {
                UsuarioPermissaoTela upt = new UsuarioPermissaoTela();
                upt.setUsuario(usuario);
                upt.setTela(tela);
                upt.setPermissao(permissaoVisualizar);
                usuarioPermissaoTelaRepository.save(upt);
            }
        }

        System.out.println("✔ Dados iniciais carregados com sucesso.");
    }

}
