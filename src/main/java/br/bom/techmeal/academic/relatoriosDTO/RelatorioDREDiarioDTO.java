package br.bom.techmeal.academic.relatoriosDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class RelatorioDREDiarioDTO {
    private LocalDate data;
    private double receber;
    private double pagar;
    private double saldo;
    private int clientesAtendidos;

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public double getReceber() { return receber; }
    public void setReceber(double receber) { this.receber = receber; }

    public double getPagar() { return pagar; }
    public void setPagar(double pagar) { this.pagar = pagar; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public int getClientesAtendidos() { return clientesAtendidos; }
    public void setClientesAtendidos(int clientesAtendidos) { this.clientesAtendidos = clientesAtendidos; }

    public static void criarTemplateJrxmlSeNaoExistir(File jrxmlFile) throws IOException {
        if (jrxmlFile.exists()) return;
        jrxmlFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(jrxmlFile)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\"\n" +
                    "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "    xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\"\n" +
                    "    name=\"dre_diario\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"123e4567-e89b-12d3-a456-426614174002\">\n" +
                    "    <parameter name=\"titulo\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataInicio\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataFim\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"data\" class=\"java.time.LocalDate\"/>\n" +
                    "    <field name=\"receber\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"pagar\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"saldo\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"clientesAtendidos\" class=\"java.lang.Integer\"/>\n" +
                    "    <title>\n" +
                    "        <band height=\"50\">\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"555\" height=\"30\"/>\n" +
                    "                <textElement textAlignment=\"Center\"/>\n" +
                    "                <text><![CDATA[DRE DIÁRIO]]></text>\n" +
                    "            </staticText>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"30\" width=\"555\" height=\"20\"/>\n" +
                    "                <textElement textAlignment=\"Center\"/>\n" +
                    "                <textFieldExpression><![CDATA[\"Período: \" + $P{dataInicio} + \" a \" + $P{dataFim}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "        </band>\n" +
                    "    </title>\n" +
                    "    <columnHeader>\n" +
                    "        <band height=\"20\">\n" +
                    "            <staticText><reportElement x=\"0\" y=\"0\" width=\"80\" height=\"20\"/><text><![CDATA[Data]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"80\" y=\"0\" width=\"90\" height=\"20\"/><text><![CDATA[Receber]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"170\" y=\"0\" width=\"90\" height=\"20\"/><text><![CDATA[Pagar]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"260\" y=\"0\" width=\"90\" height=\"20\"/><text><![CDATA[Saldo]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"350\" y=\"0\" width=\"120\" height=\"20\"/><text><![CDATA[Clientes Atendidos]]></text></staticText>\n" +
                    "        </band>\n" +
                    "    </columnHeader>\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField><reportElement x=\"0\" y=\"0\" width=\"80\" height=\"20\"/><textFieldExpression><![CDATA[$F{data}.toString()]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"80\" y=\"0\" width=\"90\" height=\"20\"/><textFieldExpression><![CDATA[$F{receber}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"170\" y=\"0\" width=\"90\" height=\"20\"/><textFieldExpression><![CDATA[$F{pagar}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"260\" y=\"0\" width=\"90\" height=\"20\"/><textFieldExpression><![CDATA[$F{saldo}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"350\" y=\"0\" width=\"120\" height=\"20\"/><textFieldExpression><![CDATA[$F{clientesAtendidos}]]></textFieldExpression></textField>\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "</jasperReport>\n");
        }
    }
}
