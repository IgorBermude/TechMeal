package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.entity.Produto;
import br.bom.techmeal.academic.repository.ProdutoRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    public void inserir(ProdutoDTO produto){
        Produto produtoEntity = new Produto(produto);
        produtoRepository.save(produtoEntity);
    }

    public ProdutoDTO alterar(ProdutoDTO produto){
        Produto produtoEntity = new Produto(produto);
        return new ProdutoDTO(produtoRepository.save(produtoEntity));
    }

    public void excluir(Integer id){
        Produto produtoEntity = produtoRepository.findById(id).get();
        produtoRepository.delete(produtoEntity);
    }

    public ProdutoDTO buscarPorId(Integer id){
        return new ProdutoDTO(produtoRepository.findById(id).get());
    }

    private static int contadorItem = 1; // Variável para autoincremento
    public String gerarCodigoDeBarras() throws IOException {
        // Código do país (Brasil)
        String codigoPais = "789";

        // Número da empresa (exemplo)
        String codigoEmpresa = "835741";

        // Descrição do item (autoincremento)
        String descricaoItem = String.format("%03d", contadorItem++);

        // Concatenar código do país, empresa e descrição do item
        String codigoSemDigito = codigoPais + codigoEmpresa + descricaoItem;

        // Calcular dígito verificador
        String digitoVerificador = calcularDigitoVerificador(codigoSemDigito);

        // Concatenar tudo para formar o código de barras completo
        String numeroCodigoDeBarras = codigoSemDigito + digitoVerificador;

        if (numeroCodigoDeBarras == null || numeroCodigoDeBarras.isEmpty()) {
            throw new IllegalArgumentException("Código de barras inválido.");
        }

        // Gerar o código de barras usando ZXing
        // Cria uma matriz de bits para o código de barras
        BitMatrix bitMatrix = new Code128Writer().encode(numeroCodigoDeBarras, BarcodeFormat.CODE_128, 300, 100);

        // Criar diretório para armazenar códigos de barras
        String diretorio = "codigos-de-barras/";
        Path caminhoDiretorio = Paths.get(diretorio);
        if (!Files.exists(caminhoDiretorio)) {
            Files.createDirectories(caminhoDiretorio);
        }

        // Caminho do arquivo gerado
        String nomeArquivo = diretorio + "codigo_" + numeroCodigoDeBarras + ".png";

        // Converte a matriz de bits em uma imagem e salva no caminho especificado
        Path path = FileSystems.getDefault().getPath(nomeArquivo);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        System.out.println("Código de barras gerado com sucesso: " + nomeArquivo);

        return numeroCodigoDeBarras; // Retorna o número do código gerado
    }

    //Calculando o digito verificador a partir do modulo 10
    public String calcularDigitoVerificador(String codigo) {
        int soma = 0;
        for (int i = 0; i < codigo.length(); i++) {
            int digito = Character.getNumericValue(codigo.charAt(i));
            if (i % 2 == 0) {
                digito *= 3; // Multiplica por 3 nas posições ímpares (considerando índice 0 como 1)
            }
            soma += digito;
        }
        int resto = soma % 10;
        int digitoVerificador = (resto == 0) ? 0 : 10 - resto;
        return String.valueOf(digitoVerificador);
    }
}
