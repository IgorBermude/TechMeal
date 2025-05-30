package br.bom.techmeal.academic.relatoriosDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RelatorioVendasPorProdutoDTO {
    private String nomeProduto;
    private int quantidadeVendida;
    private double valorTotal;

    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public static void criarTemplateJrxmlSeNaoExistir(File jrxmlFile) throws IOException {
        if (jrxmlFile.exists()) return;
        jrxmlFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(jrxmlFile)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\"\n" +
                    "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "    xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\"\n" +
                    "    name=\"vendas_por_produto\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"123e4567-e89b-12d3-a456-426614174001\">\n" +
                    "    <parameter name=\"titulo\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataInicio\" class=\"java.lang.String\"/>\n" +
                    "    <parameter name=\"dataFim\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"nomeProduto\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"quantidadeVendida\" class=\"java.lang.Integer\"/>\n" +
                    "    <field name=\"valorTotal\" class=\"java.lang.Double\"/>\n" +
                    "    <title>\n" +
                    "        <band height=\"50\">\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"555\" height=\"30\"/>\n" +
                    "                <textElement textAlignment=\"Center\"/>\n" +
                    "                <text><![CDATA[VENDAS POR PRODUTO]]></text>\n" +
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
                    "            <staticText><reportElement x=\"0\" y=\"0\" width=\"200\" height=\"20\"/><text><![CDATA[Produto]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"200\" y=\"0\" width=\"100\" height=\"20\"/><text><![CDATA[Quantidade]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"300\" y=\"0\" width=\"100\" height=\"20\"/><text><![CDATA[Valor Total]]></text></staticText>\n" +
                    "        </band>\n" +
                    "    </columnHeader>\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField><reportElement x=\"0\" y=\"0\" width=\"200\" height=\"20\"/><textFieldExpression><![CDATA[$F{nomeProduto}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"200\" y=\"0\" width=\"100\" height=\"20\"/><textFieldExpression><![CDATA[$F{quantidadeVendida}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"300\" y=\"0\" width=\"100\" height=\"20\"/><textFieldExpression><![CDATA[$F{valorTotal}]]></textFieldExpression></textField>\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "</jasperReport>\n");
        }
    }
}
