package br.bom.techmeal.academic.relatoriosDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RelatorioClientesDevedoresDTO {
    private String nomeCliente;
    private double faturaCliente;
    private double saldoCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getFaturaCliente() {
        return Double.isNaN(faturaCliente) ? 0 : faturaCliente;
    }

    public void setFaturaCliente(double faturaCliente) {
        this.faturaCliente = Double.isNaN(faturaCliente) ? 0 : faturaCliente;
    }

    public double getSaldoCliente() {
        return Double.isNaN(saldoCliente) ? 0 : saldoCliente;
    }

    public void setSaldoCliente(double saldoCliente) {
        this.saldoCliente = Double.isNaN(saldoCliente) ? 0 : saldoCliente;
    }

    // Cria o template jrxml se não existir
    public static void criarTemplateJrxmlSeNaoExistir(File jrxmlFile) throws IOException {
        if (!jrxmlFile.exists()) {
            jrxmlFile.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(jrxmlFile)) {
                writer.write(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"clientes_devedores\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"e1e2e3e4-e5e6-4e7e-8e9e-e0e1e2e3e4e5\">\n" +
                    "    <parameter name=\"titulo\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"nomeCliente\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"faturaCliente\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"saldoCliente\" class=\"java.lang.Double\"/>\n" +
                    "    <title>\n" +
                    "        <band height=\"40\">\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"555\" height=\"30\"/>\n" +
                    "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">\n" +
                    "                    <font size=\"18\" isBold=\"true\"/>\n" +
                    "                </textElement>\n" +
                    "                <textFieldExpression><![CDATA[$P{titulo}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "        </band>\n" +
                    "    </title>\n" +
                    "    <columnHeader>\n" +
                    "        <band height=\"20\">\n" +
                    "            <staticText><reportElement x=\"0\" y=\"0\" width=\"250\" height=\"20\"/><text><![CDATA[Cliente]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"250\" y=\"0\" width=\"150\" height=\"20\"/><text><![CDATA[Fatura]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"400\" y=\"0\" width=\"155\" height=\"20\"/><text><![CDATA[Saldo]]></text></staticText>\n" +
                    "        </band>\n" +
                    "    </columnHeader>\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField><reportElement x=\"0\" y=\"0\" width=\"250\" height=\"20\"/><textFieldExpression><![CDATA[$F{nomeCliente}]]></textFieldExpression></textField>\n" +
                    "            <textField pattern=\"#,##0.00\"><reportElement x=\"250\" y=\"0\" width=\"150\" height=\"20\"/><textFieldExpression><![CDATA[$F{faturaCliente}]]></textFieldExpression></textField>\n" +
                    "            <textField pattern=\"#,##0.00\"><reportElement x=\"400\" y=\"0\" width=\"155\" height=\"20\"/><textFieldExpression><![CDATA[$F{saldoCliente}]]></textFieldExpression></textField>\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "</jasperReport>\n"
                );
            }
        }
    }
}
