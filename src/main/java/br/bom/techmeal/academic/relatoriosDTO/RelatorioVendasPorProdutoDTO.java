package br.bom.techmeal.academic.relatoriosDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RelatorioVendasPorProdutoDTO {
    private String nomeProduto;
    private int quantidadeVendida;
    private double valorVenda;
    private double valorCusto;
    private double lucro;

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
    public double getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    public double getValorCusto() {
        return valorCusto;
    }
    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }
    public double getLucro() {
        return lucro;
    }
    public void setLucro(double lucro) {
        this.lucro = lucro;
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
                    "    <field name=\"valorVenda\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"valorCusto\" class=\"java.lang.Double\"/>\n" +
                    "    <field name=\"lucro\" class=\"java.lang.Double\"/>\n" +
                    "    <title>\n" +
                    "        <band height=\"50\">\n" +
                    "            <staticText>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"555\" height=\"30\"/>\n" +
                    "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">\n" +
                    "                    <font size=\"16\" isBold=\"true\"/>\n" +
                    "                </textElement>\n" +
                    "                <text><![CDATA[RELATÓRIO DE VENDAS POR PRODUTO]]></text>\n" +
                    "            </staticText>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"30\" width=\"555\" height=\"20\"/>\n" +
                    "                <textElement textAlignment=\"Center\"/>\n" +
                    "                <textFieldExpression><![CDATA[\"Período: \" + $P{dataInicio} + \" a \" + $P{dataFim}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "        </band>\n" +
                    "    </title>\n" +
                    "    <columnHeader>\n" +
                    "        <band height=\"22\">\n" +
                    "            <staticText><reportElement x=\"0\" y=\"0\" width=\"150\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/><box><pen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement><text><![CDATA[Produto]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"150\" y=\"0\" width=\"80\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/><box><pen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement><text><![CDATA[Quantidade]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"230\" y=\"0\" width=\"80\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/><box><pen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement><text><![CDATA[Valor Venda]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"310\" y=\"0\" width=\"80\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/><box><pen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement><text><![CDATA[Valor Custo]]></text></staticText>\n" +
                    "            <staticText><reportElement x=\"390\" y=\"0\" width=\"80\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/><box><pen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement><text><![CDATA[Lucro]]></text></staticText>\n" +
                    "        </band>\n" +
                    "    </columnHeader>\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField><reportElement x=\"0\" y=\"0\" width=\"150\" height=\"20\"/><box><pen lineWidth=\"0.5\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{nomeProduto}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"150\" y=\"0\" width=\"80\" height=\"20\"/><box><pen lineWidth=\"0.5\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{quantidadeVendida}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"230\" y=\"0\" width=\"80\" height=\"20\"/><box><pen lineWidth=\"0.5\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{valorVenda}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"310\" y=\"0\" width=\"80\" height=\"20\"/><box><pen lineWidth=\"0.5\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{valorCusto}]]></textFieldExpression></textField>\n" +
                    "            <textField><reportElement x=\"390\" y=\"0\" width=\"80\" height=\"20\"/><box><pen lineWidth=\"0.5\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{lucro}]]></textFieldExpression></textField>\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "</jasperReport>\n");
        }
    }
}
