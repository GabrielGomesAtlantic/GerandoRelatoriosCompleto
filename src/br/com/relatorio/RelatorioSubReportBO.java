package br.com.relatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.vo.PersonagemVO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe responsável por gerar os relatórios que possuem "subreports".
 * @author Gabriel Gomes/Renan Watanbe
 * @since 31/01/2022
 */
public class RelatorioSubReportBO {
	
	public static void main(String[] args) throws JRException {
		gerarRelatorioSubReportCompleto();
	}
	
	public static final String DIR_EXEMPLO_RELATORIO_JASPER = "relatorio/RelatorioA.jrxml";
	public static final String DIR_EXEMPLO_RELATORIO_JASPER_SUBREPORT = "C:/development/workspaces/Desenvolvimento/RelatorioExemplo/relatorio/RelatorioASubReport.jasper";

	public static final String DIR_EXEMPLO_RELATORIO_JRXML = "relatorio/RelatorioB.jrxml";
	public static final String DIR_EXEMPLO_RELATORIO_JRXML_SUBREPORT = "relatorio/RelatorioBSubReport.jrxml";
	
	public static final String DIR_EXEMPLO_COMPLETO = "relatorio/RelatorioTudo.jrxml";
	public static final String DIR_EXEMPLO_COMPLETO_SUBREPORT = "relatorio/RelatorioTudoSubReport.jrxml";

	//public static final String DIR_EXEMPLO_B_SUBREPORT = "C:/development/workspaces/Desenvolvimento/RelatorioExemplo/relatorio/RelatorioBSubReport.jrxml";
	
	/**
	 * Caso que funciona com .jasper.
	 */
	public static void gerarRelatorioSubReportJasper() throws JRException {
		Map<String,Object> mapaValores = new HashMap<String, Object>();
		mapaValores.put("SUB_REPORT_DIRETORIO", DIR_EXEMPLO_RELATORIO_JASPER_SUBREPORT);
		JasperReport compilado = JasperCompileManager.compileReport(DIR_EXEMPLO_RELATORIO_JASPER);
		JasperPrint print = JasperFillManager.fillReport(compilado, mapaValores, new JREmptyDataSource());
		JasperViewer.viewReport(print, true);
	}
	
	/**
	 * Caso que funciona com .jrxml.
	 */
	public static void gerarRelatorioSubReportJrxml() throws JRException {
		Map<String,Object> mapaValores = new HashMap<String, Object>();
		
		//Compilação dinâmica de um subrelatório .jrxml -> .jasper
		JasperReport subCompilado = JasperCompileManager.compileReport(DIR_EXEMPLO_RELATORIO_JRXML_SUBREPORT);
		Object view = subCompilado;
		mapaValores.put("SUB_REPORT_DIRETORIO", view);
		
		//mapaValores.put("SUB_REPORT_DIRETORIO", DIR_EXEMPLO_B_SUBREPORT);
		JasperReport compilado = JasperCompileManager.compileReport(DIR_EXEMPLO_RELATORIO_JRXML);
		JasperPrint print = JasperFillManager.fillReport(compilado, mapaValores, new JREmptyDataSource());
		JasperViewer.viewReport(print, true);
		
	}
	
	/*
	 * Caso que gera um relatório completo, com parâmetros, listas e subrelatórios 
	 * */
	public static void gerarRelatorioSubReportCompleto() throws JRException {
		Map<String,Object> mapaValores = new HashMap<String, Object>();
		mapaValores.put("nomeClan", "Insanity");
		
		List<PersonagemVO> listaPersonagens = new ArrayList<PersonagemVO>();
		listaPersonagens.add(new PersonagemVO("Nick A", 85));
		listaPersonagens.add(new PersonagemVO("Nick B", 95));
		
		mapaValores.put("listaPersonagens", listaPersonagens);

		JasperReport subCompilado = JasperCompileManager.compileReport(DIR_EXEMPLO_COMPLETO_SUBREPORT);
		Object view = subCompilado;
		mapaValores.put("SUB_REPORT_DIRETORIO", view);
		
		JasperReport compilado = JasperCompileManager.compileReport(DIR_EXEMPLO_COMPLETO);
		JasperPrint print = JasperFillManager.fillReport(compilado, mapaValores, new JRBeanCollectionDataSource(listaPersonagens));
		JasperViewer.viewReport(print, true);
		
	}
} 
