package br.bom.techmeal.academic.controller;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import br.bom.techmeal.academic.relatoriosDTO.RelatorioTicketMedioDTO;
import br.bom.techmeal.academic.repository.ClienteRepository;
import br.bom.techmeal.academic.repository.HistoricoRecargaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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

            for (HistoricoRecarga historico : historicos) {
                totalGasto += historico.getValorRecargaHistoricoRecarga();
            }

            double ticketMedio = totalGasto / totalClientes;

            RelatorioTicketMedioDTO dto = new RelatorioTicketMedioDTO();
            dto.setNomeCliente(cliente.getNomeCliente());
            dto.setTotalGasto(totalGasto);
            dto.setTotalPagar(totalPagar);
            dto.setSaldo(saldo);
            dto.setTicketMedio(ticketMedio);
            dto.setDataInicio(inicio);
            dto.setDataFim(fim);

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

        try (java.io.FileOutputStream fos = new java.io.FileOutputStream("ticket_medio_teste.pdf")) {
            fos.write(outputStream.toByteArray());
        }

        return outputStream.toByteArray();
    }
}
