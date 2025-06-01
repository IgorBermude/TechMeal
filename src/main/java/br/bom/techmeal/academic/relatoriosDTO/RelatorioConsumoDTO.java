package br.bom.techmeal.academic.relatoriosDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class RelatorioConsumoDTO {
    private String nomeCliente;
    private double totalConsumido;
    private int totalCompras;
    private int totalRecargas;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double saldoCliente;
    private double faturaCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public double getTotalConsumido() {
        return totalConsumido;
    }
    public void setTotalConsumido(double totalConsumido) {
        this.totalConsumido = totalConsumido;
    }
    public int getTotalCompras() {
        return totalCompras;
    }
    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }
    public int getTotalRecargas() {
        return totalRecargas;
    }
    public void setTotalRecargas(int totalRecargas) {
        this.totalRecargas = totalRecargas;
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
    public double getSaldoCliente() {
        return saldoCliente;
    }
    public void setSaldoCliente(double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }
    public double getFaturaCliente() {
        return faturaCliente;
    }
    public void setFaturaCliente(double faturaCliente) {
        this.faturaCliente = faturaCliente;
    }

    public static void criarTemplateJrxmlSeNaoExistir(File jrxmlFile) throws IOException {
        if (jrxmlFile.exists()) return;
        jrxmlFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(jrxmlFile)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"consumo_cliente\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"b1b2c3d4-e5f6-7890-abcd-1234567890ab\">\n" +
                    "    <parameter name=\"titulo\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataInicio\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataFim\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"nomeCliente\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"totalConsumido\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"totalCompras\" class=\"java.lang.Integer\"/>\n" +
                    "    <field name=\"totalRecargas\" class=\"java.lang.Integer\"/>\n" +
                    "    <field name=\"saldoCliente\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"faturaCliente\" class=\"java.lang.Double\"/>\n" +
                    "    <title>\n" +
                    "        <band height=\"50\">\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"555\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Relatório de Consumo por Cliente]]></text>\n" +
                    "            </staticText>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"25\" width=\"555\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[\"Período: \" + $P{dataInicio} + \" a \" + $P{dataFim}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "        </band>\n" +
                    "    </title>\n" +
                    "    <columnHeader>\n" +
                    "        <band height=\"20\">\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"120\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Cliente]]></text>\n" +
                    "            </staticText>\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"120\" y=\"0\" width=\"80\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Consumido]]></text>\n" +
                    "            </staticText>\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"200\" y=\"0\" width=\"70\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Compras]]></text>\n" +
                    "            </staticText>\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"270\" y=\"0\" width=\"70\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Recargas]]></text>\n" +
                    "            </staticText>\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"340\" y=\"0\" width=\"80\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Fatura]]></text>\n" +
                    "            </staticText>\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"420\" y=\"0\" width=\"80\" height=\"20\"/>\n" +
                    "                <text><![CDATA[Saldo]]></text>\n" +
                    "            </staticText>\n" +
                    "        </band>\n" +
                    "    </columnHeader>\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"120\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{nomeCliente}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField pattern=\"#,##0.00\">\n" +
                    "                <reportElement x=\"120\" y=\"0\" width=\"80\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{totalConsumido}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"200\" y=\"0\" width=\"70\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{totalCompras}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"270\" y=\"0\" width=\"70\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{totalRecargas}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField pattern=\"#,##0.00\">\n" +
                    "                <reportElement x=\"340\" y=\"0\" width=\"80\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{faturaCliente}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField pattern=\"#,##0.00\">\n" +
                    "                <reportElement x=\"420\" y=\"0\" width=\"80\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{saldoCliente}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "</jasperReport>\n");
        }
    }
}
