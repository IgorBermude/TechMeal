package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import br.bom.techmeal.academic.relatoriosDTO.RelatorioAniversariantesDiaDTO;
import br.bom.techmeal.academic.relatoriosDTO.RelatorioTicketMedioDTO;
import br.bom.techmeal.academic.relatoriosDTO.RelatorioVendasPorProdutoDTO;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.ComandaProdutoRepository;
import br.bom.techmeal.academic.repository.HistoricoRecargaRepository;
import br.bom.techmeal.academic.repository.ProdutoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HistoricoRecargaRepository historicoRecargaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Tiket medio pega o total gasto e divide pelo total de dias que ele comprou
    @PostMapping("/ticket-medio-clientes")
    public byte[] gerarRelatorioTicketMedioClientes(LocalDate inicio, LocalDate fim) throws JRException, IOException {
        List<Cliente> clientes = clienteRepository.findAll();
        List<RelatorioTicketMedioDTO> dados = new java.util.ArrayList<>();

        int totalClientes = clientes.size();

        for (Cliente cliente : clientes) {
            List<HistoricoRecarga> historicos = historicoRecargaRepository.findByClienteAndDataRecargaBetween(
                cliente.getIdCliente(), inicio, fim
            );

            double totalGasto = 0;
            double totalPagar = cliente.getFaturaCliente();
            double saldo = cliente.getSaldoCliente();
            int qtd = 0;

            for (HistoricoRecarga historico : historicos) {
                totalGasto += historico.getValorRecargaHistoricoRecarga();
                qtd++;
            }

            double ticketMedio = totalGasto / qtd;

            RelatorioTicketMedioDTO dto = new RelatorioTicketMedioDTO();
            dto.setNomeCliente(cliente.getNomeCliente());
            dto.setTotalGasto(totalGasto);
            dto.setTotalPagar(totalPagar);
            dto.setSaldo(saldo);
            dto.setTicketMedio(ticketMedio);
            dto.setDataInicio(inicio);
            dto.setDataFim(fim);
            dto.setQtd(qtd);

            dados.add(dto);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatório de Ticket Médio por Cliente");
        parametros.put("dataInicio", inicio.toString());
        parametros.put("dataFim", fim.toString());
        parametros.put("totalClientes", totalClientes);

        String jrxmlPath = "src/main/resources/br/bom/techmeal/academic/relatorios/ticket_medio.jrxml";
        File jrxmlFile = new File(jrxmlPath);

        RelatorioTicketMedioDTO.criarTemplateJrxmlSeNaoExistir(jrxmlFile);

        InputStream jrxml = getClass().getClassLoader().getResourceAsStream(jrxmlPath);
        if (jrxml == null) {
            jrxml = new java.io.FileInputStream(jrxmlFile);
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        /*try (java.io.FileOutputStream fos = new java.io.FileOutputStream("ticket_medio_teste.pdf")) {
            fos.write(outputStream.toByteArray());
        }*/

        return outputStream.toByteArray();
    }

    @PostMapping("/aniversariantes-dia")
    public byte[] gerarRelatorioAniversariantesDia(
            @RequestParam("dia") int dia,
            @RequestParam("mes") int mes
    ) throws JRException, IOException {
        List<Cliente> aniversariantes = clienteRepository.findByDiaMesAniversario(dia, mes);
        List<RelatorioAniversariantesDiaDTO> dados = new java.util.ArrayList<>();

        for (Cliente cliente : aniversariantes) {
            RelatorioAniversariantesDiaDTO dto = new RelatorioAniversariantesDiaDTO();
            dto.setNomeCliente(cliente.getNomeCliente());
            dto.setDataNascimento(cliente.getDtNascCliente());
            dados.add(dto);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatório de Aniversariantes do Dia");
        parametros.put("dia", dia);
        parametros.put("mes", mes);

        String jrxmlPath = "src/main/resources/br/bom/techmeal/academic/relatorios/aniversariantes_dia.jrxml";
        File jrxmlFile = new File(jrxmlPath);

        RelatorioAniversariantesDiaDTO.criarTemplateJrxmlSeNaoExistir(jrxmlFile);

        InputStream jrxml = getClass().getClassLoader().getResourceAsStream(jrxmlPath);
        if (jrxml == null) {
            jrxml = new java.io.FileInputStream(jrxmlFile);
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }

    @PostMapping("/vendas-por-produto")
    public byte[] gerarRelatorioVendasPorProduto(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) throws JRException, IOException {
        List<Object[]> vendas = produtoRepository.findVendasPorPeriodo(dataInicio, dataFim);
        List<RelatorioVendasPorProdutoDTO> dados = new java.util.ArrayList<>();

        for (Object[] venda : vendas) {
            RelatorioVendasPorProdutoDTO dto = new RelatorioVendasPorProdutoDTO();
            dto.setNomeProduto((String) venda[0]);
            dto.setQuantidadeVendida(((Long) venda[1]).intValue());
            dto.setValorTotal(venda[2] != null ? ((Number) venda[2]).doubleValue() : 0.0);
            dados.add(dto);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatório de Vendas por Produto");
        parametros.put("dataInicio", dataInicio.toString());
        parametros.put("dataFim", dataFim.toString());

        String jrxmlPath = "src/main/resources/br/bom/techmeal/academic/relatorios/vendas_por_produto.jrxml";
        File jrxmlFile = new File(jrxmlPath);

        RelatorioVendasPorProdutoDTO.criarTemplateJrxmlSeNaoExistir(jrxmlFile);

        InputStream jrxml = getClass().getClassLoader().getResourceAsStream(jrxmlPath);
        if (jrxml == null) {
            jrxml = new java.io.FileInputStream(jrxmlFile);
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        /*try (java.io.FileOutputStream fos = new java.io.FileOutputStream("vendas-por-produto.pdf")) {
            fos.write(outputStream.toByteArray());
        }*/

        return outputStream.toByteArray();
    }
}
