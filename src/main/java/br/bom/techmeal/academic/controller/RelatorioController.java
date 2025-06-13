package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import br.bom.techmeal.academic.relatoriosDTO.*;
import br.bom.techmeal.academic.repository.*;
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
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

    @Autowired
    private ControleContasRepository controleContasRepository;

    @Autowired
    private ComandaRepository comandaRepository;

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

            double ticketMedio = qtd == 0 ? 0 : totalGasto / qtd;
            if (Double.isNaN(ticketMedio)) ticketMedio = 0;
            if (Double.isNaN(totalGasto)) totalGasto = 0;
            if (Double.isNaN(totalPagar)) totalPagar = 0;
            if (Double.isNaN(saldo)) saldo = 0;

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

    // Colocar o valor unitario e o valor de custo unitario no relatorio
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
            dto.setQuantidadeVendida(((Number) venda[1]).intValue());
            dto.setValorVenda(venda[2] != null ? ((Number) venda[2]).doubleValue() : 0.0);
            dto.setValorCusto(venda[3] != null ? ((Number) venda[3]).doubleValue() : 0.0);
            dto.setLucro(venda[4] != null ? ((Number) venda[4]).doubleValue() : 0.0);
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

    // Adicionar a coluna com saldo anterior e ir somando com o campo Saldo.
    @PostMapping("/dred-diario")
    public byte[] gerarRelatorioDREDiario(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) throws JRException, IOException {
        List<Cliente> clientes = clienteRepository.findAll();
        List<RelatorioDREDiarioDTO> dados = new java.util.ArrayList<>();

        // Buscar saldo anterior ao período pesquisado
        double saldoAnterior = 0.0;
        if (dataInicio != null) {
            // Soma todas as recargas antes do período
            double totalRecebidoAntes = historicoRecargaRepository
                    .findByDataRecargaBefore(dataInicio)
                    .stream()
                    .mapToDouble(HistoricoRecarga::getValorRecargaHistoricoRecarga)
                    .sum();

            // Soma todos os pagamentos de fornecedores antes do período
            double totalGastoAntes = controleContasRepository
                    .findByDataPagamentoBefore(dataInicio)
                    .stream()
                    .mapToDouble(ControleContas::getValorControleContas)
                    .sum();

            saldoAnterior = totalRecebidoAntes - totalGastoAntes;
        }

        double saldoAcumulado = saldoAnterior;
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
        for (int i = 0; i < dias; i++) {
            LocalDate dia = dataInicio.plusDays(i);

            // Total recebido no dia (recargas)
            double totalRecebido = historicoRecargaRepository
                    .findByDataRecarga(dia)
                    .stream()
                    .mapToDouble(HistoricoRecarga::getValorRecargaHistoricoRecarga)
                    .sum();

            // Total gasto no dia (contas de fornecedores pagas na data)
            double totalGasto = controleContasRepository
                    .findByDataPagamento(dia)
                    .stream()
                    .mapToDouble(ControleContas::getValorControleContas)
                    .sum();

            // Resultado do dia (não acumulativo)
            double resultado = totalRecebido - totalGasto;

            RelatorioDREDiarioDTO dre = new RelatorioDREDiarioDTO();
            dre.setData(dia);
            dre.setReceber(totalRecebido);
            dre.setPagar(totalGasto);
            dre.setResultado(resultado); // resultado do dia (não acumulativo)
            dre.setSaldo(saldoAcumulado); // saldo anterior/acumulado

            saldoAcumulado += resultado; // saldo acumulado para o próximo dia

            dados.add(dre);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "DRE Diário");
        parametros.put("dataInicio", dataInicio.toString());
        parametros.put("dataFim", dataFim.toString());
        parametros.put("saldoAnterior", saldoAnterior); // Adicionado parâmetro saldoAnterior

        String jrxmlPath = "src/main/resources/br/bom/techmeal/academic/relatorios/dre_diario.jrxml";
        File jrxmlFile = new File(jrxmlPath);

        RelatorioDREDiarioDTO.criarTemplateJrxmlSeNaoExistir(jrxmlFile);

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

    @PostMapping("/consumo")
    public byte[] gerarRelatorioConsumo(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) throws JRException, IOException {
        List<Cliente> clientes = clienteRepository.findAll();
        List<RelatorioConsumoDTO> dados = new java.util.ArrayList<>();

        for (Cliente cliente : clientes) {
            List<HistoricoRecarga> historicos = historicoRecargaRepository.findByClienteAndDataRecargaBetween(
                cliente.getIdCliente(), dataInicio, dataFim
            );

            double totalConsumido = historicos.stream()
                .mapToDouble(HistoricoRecarga::getValorRecargaHistoricoRecarga)
                .sum();

            int totalCompras = produtoRepository.somarProdutosCompradosPorClienteEPeriodo(
                cliente.getIdCliente(), dataInicio, dataFim
            );

            int totalRecargas = historicos.size();

            RelatorioConsumoDTO dto = new RelatorioConsumoDTO();
            dto.setNomeCliente(cliente.getNomeCliente());
            dto.setTotalConsumido(totalConsumido);
            dto.setTotalCompras(totalCompras);
            dto.setTotalRecargas(totalRecargas);
            dto.setSaldoCliente(cliente.getSaldoCliente());
            dto.setFaturaCliente(cliente.getFaturaCliente());
            dto.setDataInicio(dataInicio);
            dto.setDataFim(dataFim);
            dados.add(dto);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatório de Consumo por Cliente");
        parametros.put("dataInicio", dataInicio.toString());
        parametros.put("dataFim", dataFim.toString());

        String jrxmlPath = "src/main/resources/br/bom/techmeal/academic/relatorios/consumo_cliente.jrxml";
        File jrxmlFile = new File(jrxmlPath);

        RelatorioConsumoDTO.criarTemplateJrxmlSeNaoExistir(jrxmlFile);

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

    @PostMapping("/clientes-devedores")
    public byte[] gerarRelatorioClientesDevedores() throws JRException, IOException {
        List<Cliente> clientes = clienteRepository.findAll();
        List<RelatorioClientesDevedoresDTO> dados = new java.util.ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for (Cliente cliente : clientes) {
            // Usa o atributo ultimaCompraCliente do Cliente
            Date dataUltimaCompra = cliente.getUltimaCompraCliente();
            boolean devedor = false;
            // Critério: faturaCliente diferente de limiteCliente
            if (cliente.getFaturaCliente() != cliente.getLimiteCliente()) {
                if (dataUltimaCompra != null) {
                    LocalDate dataUltimaCompraLocalDate;
                    if (dataUltimaCompra instanceof java.sql.Date) {
                        dataUltimaCompraLocalDate = ((java.sql.Date) dataUltimaCompra).toLocalDate();
                    } else {
                        dataUltimaCompraLocalDate = dataUltimaCompra.toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();
                    }
                    long diasSemComprar = java.time.temporal.ChronoUnit.DAYS.between(dataUltimaCompraLocalDate, hoje);
                    if (diasSemComprar > 30) {
                        devedor = true;
                    }
                } else {
                    // Nunca comprou, considera devedor
                    devedor = true;
                }
            }

            if (devedor) {
                RelatorioClientesDevedoresDTO dto = new RelatorioClientesDevedoresDTO();
                dto.setNomeCliente(cliente.getNomeCliente());
                dto.setFaturaCliente(cliente.getFaturaCliente());
                dto.setSaldoCliente(cliente.getSaldoCliente());
                dto.setLimiteCliente(cliente.getLimiteCliente());
                dados.add(dto);
            }
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatório de Clientes Devedores");

        String jrxmlPath = "src/main/resources/br/bom/techmeal/academic/relatorios/clientes_devedores.jrxml";
        File jrxmlFile = new File(jrxmlPath);

        RelatorioClientesDevedoresDTO.criarTemplateJrxmlSeNaoExistir(jrxmlFile);

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

    @GetMapping("/consumo-grafico")
    public List<RelatorioConsumoDTO> gerarGraficoConsumo() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<RelatorioConsumoDTO> dados = new java.util.ArrayList<>();

        for (Cliente cliente : clientes) {
            List<Comanda> comandas = comandaRepository.findByCliente(cliente);

            double totalConsumido = comandas.stream()
                    .mapToDouble(Comanda::getValorTotalComanda)
                    .sum();

            int totalCompras = produtoRepository.somarProdutosCompradosPorCliente(cliente.getIdCliente());

            int totalRecargas = comandas.size();

            RelatorioConsumoDTO dto = new RelatorioConsumoDTO();
            dto.setNomeCliente(cliente.getNomeCliente());
            dto.setTotalConsumido(totalConsumido);
            dto.setTotalCompras(totalCompras);
            dto.setTotalRecargas(totalRecargas);
            dto.setSaldoCliente(cliente.getSaldoCliente());
            dto.setFaturaCliente(cliente.getFaturaCliente());
            dados.add(dto);
        }

        return dados;
    }
}