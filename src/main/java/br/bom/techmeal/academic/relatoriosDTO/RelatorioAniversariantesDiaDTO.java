package br.bom.techmeal.academic.relatoriosDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class RelatorioAniversariantesDiaDTO {
    private String nomeCliente;
    private Date dataNascimento;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public static void criarTemplateJrxmlSeNaoExistir(File jrxmlFile) {
        if (!jrxmlFile.exists()) {
            jrxmlFile.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(jrxmlFile)) {
                writer.write(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\"\n" +
                                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                                "    xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\"\n" +
                                "    name=\"aniversariantes_dia\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"123e4567-e89b-12d3-a456-426614174000\">\n" +
                                "    <parameter name=\"titulo\" class=\"java.lang.String\"/>\n" +
                                "    <parameter name=\"dia\" class=\"java.lang.Integer\"/>\n" +
                                "    <parameter name=\"mes\" class=\"java.lang.Integer\"/>\n" +
                                "    <field name=\"nomeCliente\" class=\"java.lang.String\"/>\n" +
                                "    <field name=\"dataNascimento\" class=\"java.util.Date\"/>\n" +
                                "    <title>\n" +
                                "        <band height=\"50\">\n" +
                                "            <staticText>\n" +
                                "                <reportElement x=\"0\" y=\"0\" width=\"555\" height=\"20\"/>\n" +
                                "                <textElement textAlignment=\"Center\"/>\n" +
                                "                <text><![CDATA[RELATORIO ANIVERSARIANTES DO DIA]]></text>\n" +
                                "            </staticText>\n" +
                                "            <textField>\n" +
                                "                <reportElement x=\"0\" y=\"25\" width=\"555\" height=\"20\"/>\n" +
                                "                <textElement textAlignment=\"Center\"/>\n" +
                                "                <textFieldExpression><![CDATA[\"Data: \" + $P{dia} + \"/\" + $P{mes}]]></textFieldExpression>\n" +
                                "            </textField>\n" +
                                "        </band>\n" +
                                "    </title>\n" +
                                "    <columnHeader>\n" +
                                "        <band height=\"22\">\n" +
                                "            <staticText>\n" +
                                "                <reportElement x=\"0\" y=\"0\" width=\"350\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/>\n" +
                                "                <box><pen lineWidth=\"1.0\"/></box>\n" +
                                "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement>\n" +
                                "                <text><![CDATA[Nome do Cliente]]></text>\n" +
                                "            </staticText>\n" +
                                "            <staticText>\n" +
                                "                <reportElement x=\"350\" y=\"0\" width=\"205\" height=\"22\" backcolor=\"#E0E0E0\" mode=\"Opaque\"/>\n" +
                                "                <box><pen lineWidth=\"1.0\"/></box>\n" +
                                "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font isBold=\"true\"/></textElement>\n" +
                                "                <text><![CDATA[Data de Nascimento]]></text>\n" +
                                "            </staticText>\n" +
                                "        </band>\n" +
                                "    </columnHeader>\n" +
                                "    <detail>\n" +
                                "        <band height=\"20\">\n" +
                                "            <textField>\n" +
                                "                <reportElement x=\"0\" y=\"0\" width=\"350\" height=\"20\"/>\n" +
                                "                <box><pen lineWidth=\"0.5\"/></box>\n" +
                                "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/>\n" +
                                "                <textFieldExpression><![CDATA[$F{nomeCliente}]]></textFieldExpression>\n" +
                                "            </textField>\n" +
                                "            <textField>\n" +
                                "                <reportElement x=\"350\" y=\"0\" width=\"205\" height=\"20\"/>\n" +
                                "                <box><pen lineWidth=\"0.5\"/></box>\n" +
                                "                <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/>\n" +
                                "                <textFieldExpression><![CDATA[$F{dataNascimento} != null ? $F{dataNascimento}.toString() : \"\"]]></textFieldExpression>\n" +
                                "            </textField>\n" +
                                "        </band>\n" +
                                "    </detail>\n" +
                                "</jasperReport>\n"
                );
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar template JRXML de aniversariantes do dia", e);
            }
        }
    }
}
