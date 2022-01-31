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
 * Classe responsável por gerar os relatórios simples.
 * @author Gabriel Gomes/Renan Watanbe
 * @since 31/01/2022
 */
public class RelatorioSimplesBO {
	
	public static final String DIR_EXEMPLO_SIMPLES = "relatorio/RelatorioExemploSimples.jrxml";
	public static final String DIR_EXEMPLO_SIMPLES_COM_PARAMETRO = "relatorio/RelatorioExemploSimplesComParametro.jrxml";
	public static final String DIR_EXEMPLO_SIMPLES_COM_PARAMETRO_E_LISTA = "relatorio/RelatorioExemploSimplesComParametroELista.jrxml";
	
	
	public static void main(String[] args) throws JRException {
		gerarRelatorioExemploSimples();
	}
	/**
	 * 1º Método : Exibe um relatório que não contém  os parameters (map) e nem a collection (lista).
	 */
	public static void gerarRelatorioExemploSimples() throws JRException {
		JasperReport compilado = JasperCompileManager.compileReport(DIR_EXEMPLO_SIMPLES);
		JasperPrint print = JasperFillManager.fillReport(compilado, null, new JREmptyDataSource());
		JasperViewer.viewReport(print, true);
	}
	
	
	/**
	 * 2º Método : Exibe um relatório que contém os parameters (map) e sem a collection (lista).
	 */
	public static void gerarRelatorioExemploSimplesComParametro() throws JRException {
		Map<String,Object> mapaValores = new HashMap<String,Object>();
		mapaValores.put("nomeExemplo", "Gabriel Teste");
		JasperReport compilado = JasperCompileManager.compileReport(DIR_EXEMPLO_SIMPLES_COM_PARAMETRO);
		JasperPrint print = JasperFillManager.fillReport(compilado, mapaValores, new JREmptyDataSource());
		JasperViewer.viewReport(print, true);
	}

	/**
	 * 3º Método : Exibe um relatório que contém os parameters (map) e com a collection (lista).
	 */
	public static void gerarRelatorioExemploSimplesComParametroELista() throws JRException {
		Map<String,Object> mapaValores = new HashMap<String,Object>();
		mapaValores.put("nomeClan", "Insanity");
		
		List<PersonagemVO> listaPersonagens = new ArrayList<PersonagemVO>();
		listaPersonagens.add(new PersonagemVO("Nick A", 85));
		listaPersonagens.add(new PersonagemVO("Nick B", 95));
		
		JasperReport compilado = JasperCompileManager.compileReport(DIR_EXEMPLO_SIMPLES_COM_PARAMETRO_E_LISTA);
		JasperPrint print = JasperFillManager.fillReport(compilado, mapaValores, new JRBeanCollectionDataSource(listaPersonagens));
		JasperViewer.viewReport(print, true);
	}
	
	
	
}
