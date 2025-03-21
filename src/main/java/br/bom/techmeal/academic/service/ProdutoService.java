package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.entity.Produto;
import br.bom.techmeal.academic.repository.ProdutoRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public void inserir(ProdutoDTO produtoDTO) throws DataIntegrityViolationException {
        // Verifica se já existe um produto com o mesmo código de barras
        if (produtoRepository.existsByCodigoBarrasProduto(produtoDTO.getCodigoBarrasProduto())) {
            // Lança exceção se o código de barras já existir
            throw new DataIntegrityViolationException("Código de barras já existe.");
        }

        // Converte o ProdutoDTO para a entidade Produto
        Produto produtoEntity = new Produto(produtoDTO);

        // Salva o produto no banco de dados
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

    public String gerarCodigoDeBarras(String nomeProduto) throws IOException {
        // Código do país (Brasil)
        String codigoPais = "789";

        // Número da empresa (exemplo)
        String codigoEmpresa = "835741";

        // Usando o código hash do nomeProduto para gerar um número e pegando os 3 últimos dígitos
        int hashNomeProduto = nomeProduto.hashCode();
        String descricaoItem = String.format("%03d", Math.abs(hashNomeProduto) % 1000);

        // Concatenar código do país, empresa e descrição do item
        String codigoSemDigito = codigoPais + codigoEmpresa + descricaoItem;

        // Calcular dígito verificador
        String digitoVerificador = calcularDigitoVerificador(codigoSemDigito);

        // Código de barras final
        String numeroCodigoDeBarras = codigoSemDigito + digitoVerificador;

        // Gerar o código de barras usando ZXing
        BitMatrix bitMatrix = new Code128Writer().encode(numeroCodigoDeBarras, BarcodeFormat.CODE_128, 300, 100);

        // Criar diretório para armazenar códigos de barras
        String diretorio = "codigos-de-barras/";
        Path caminhoDiretorio = Paths.get(diretorio);
        if (!Files.exists(caminhoDiretorio)) {
            Files.createDirectories(caminhoDiretorio);
        }

        // Caminho do arquivo gerado
        String nomeArquivo = diretorio + "codigo_" + numeroCodigoDeBarras + ".png";

        // Converter a matriz de bits em uma imagem e salvar
        Path path = FileSystems.getDefault().getPath(nomeArquivo);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        System.out.println("Código de barras gerado com sucesso: " + nomeArquivo);

        return numeroCodigoDeBarras; // Retorna o número do código gerado
    }

    // Cálculo correto do dígito verificador para EAN-13
    public String calcularDigitoVerificador(String codigo) {
        int soma = 0;
        for (int i = 0; i < codigo.length(); i++) {
            int digito = Character.getNumericValue(codigo.charAt(i));

            // Índices pares multiplicam por 1, índices ímpares por 3 (da direita para a esquerda)
            if ((codigo.length() - i) % 2 == 0) {
                digito *= 3;
            }

            soma += digito;
        }

        int resto = soma % 10;
        int digitoVerificador = (resto == 0) ? 0 : 10 - resto;

        return String.valueOf(digitoVerificador);
    }

}
