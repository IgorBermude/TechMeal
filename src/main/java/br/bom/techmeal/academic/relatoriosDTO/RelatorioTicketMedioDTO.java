package br.bom.techmeal.academic.relatoriosDTO;

import java.time.LocalDate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RelatorioTicketMedioDTO {
    private String nomeCliente;
    private double totalGasto;
    private double totalPagar;
    private double saldo;
    private double ticketMedio;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int qtd;

    public RelatorioTicketMedioDTO() {
    }

    public RelatorioTicketMedioDTO(String nomeCliente, double totalGasto, double totalPagar, double saldo, double ticketMedio, LocalDate dataInicio, LocalDate dataFim) {
        this.nomeCliente = nomeCliente;
        this.totalGasto = totalGasto;
        this.totalPagar = totalPagar;
        this.saldo = saldo;
        this.ticketMedio = ticketMedio;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getTotalGasto() {
        return Double.isNaN(totalGasto) ? 0 : totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = Double.isNaN(totalGasto) ? 0 : totalGasto;
    }

    public double getTotalPagar() {
        return Double.isNaN(totalPagar) ? 0 : totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = Double.isNaN(totalPagar) ? 0 : totalPagar;
    }

    public double getSaldo() {
        return Double.isNaN(saldo) ? 0 : saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = Double.isNaN(saldo) ? 0 : saldo;
    }

    public double getTicketMedio() {
        return Double.isNaN(ticketMedio) ? 0 : ticketMedio;
    }

    public void setTicketMedio(double ticketMedio) {
        this.ticketMedio = Double.isNaN(ticketMedio) ? 0 : ticketMedio;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public static void criarTemplateJrxmlSeNaoExistir(File jrxmlFile) throws IOException {
        if (!jrxmlFile.exists()) {
            jrxmlFile.getParentFile().mkdirs();
            String jrxmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\"\n" +
                    "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "    xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\"\n" +
                    "    name=\"ticket_medio\" pageWidth=\"900\" pageHeight=\"842\" columnWidth=\"860\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"12345678-1234-1234-1234-123456789012\">\n" +
                    "    <parameter name=\"dataInicio\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataFim\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"nomeCliente\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"totalGasto\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"totalPagar\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"saldo\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"ticketMedio\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"dataInicio\" class=\"java.time.LocalDate\"/>\n" +
                    "    <field name=\"dataFim\" class=\"java.time.LocalDate\"/>\n" +
                    "    <field name=\"qtd\" class=\"java.lang.Integer\"/>\n" +
                    "    <title>\n" +
                    "        <band height=\"60\">\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"860\" height=\"30\"/>\n" +
                    "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">\n" +
                    "                    <font size=\"18\" isBold=\"true\"/>\n" +
                    "                </textElement>\n" +
                    "                <text><![CDATA[RELATORIO TICKET MÉDIO]]></text>\n" +
                    "            </staticText>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"35\" width=\"860\" height=\"20\"/>\n" +
                    "                <textElement textAlignment=\"Center\"/>\n" +
                    "                <textFieldExpression><![CDATA[\"Período: \" + $P{dataInicio} + \" a \" + $P{dataFim}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "        </band>\n" +
                    "    </title>\n" +
                    "    <columnHeader>\n" +
                    "        <band height=\"20\">\n" +
                    "            <staticText><reportElement x=\"0\" y=\"0\" width=\"150\" height=\"20\"/><textElement/><text><![CDATA[Cliente]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"150\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><text><![CDATA[Ticket Médio]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"250\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><text><![CDATA[Limite]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"350\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><text><![CDATA[Saldo Atual]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"450\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><text><![CDATA[Dias]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"550\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><text><![CDATA[Total Gasto]]></text></staticText>\n" +
                    "        </band>\n" +
                    "    </columnHeader>\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField><reportElement x=\"0\" y=\"0\" width=\"150\" height=\"20\"/><textElement/><textFieldExpression><![CDATA[$F{nomeCliente}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"150\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><textFieldExpression><![CDATA[String.format(\"%.2f\", $F{ticketMedio})]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"250\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><textFieldExpression><![CDATA[String.format(\"%.2f\", $F{totalPagar})]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"350\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><textFieldExpression><![CDATA[String.format(\"%.2f\", $F{saldo})]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"450\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><textFieldExpression><![CDATA[$F{qtd}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"550\" y=\"0\" width=\"100\" height=\"20\"/><textElement/><textFieldExpression><![CDATA[String.format(\"%.2f\", $F{totalGasto})]]></textFieldExpression></textField>\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "</jasperReport>\n";
            try (FileOutputStream fos = new FileOutputStream(jrxmlFile)) {
                fos.write(jrxmlContent.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
