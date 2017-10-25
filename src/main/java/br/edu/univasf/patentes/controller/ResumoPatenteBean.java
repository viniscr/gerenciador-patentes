package br.edu.univasf.patentes.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.util.jsf.FacesUtil;
import br.edu.univasf.patentes.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class ResumoPatenteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;
	
	private Patente patente;
	
	public ResumoPatenteBean(){
		patente = new Patente();
	}
	
	public void emitirResumo() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("patente_id", this.patente.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/resumo.jasper",
				this.response, parametros, "Resumo.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			System.out.println(this.patente.getId());
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	public void emitirRelatorioDescritivo(){
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("patente_id", this.patente.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio.jasper",
				this.response, parametros, "RelatorioDescritivo.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	public void emitirReivindicacoes(){
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("patente_id", this.patente.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/reivindicacoes.jasper",
				this.response, parametros, "Reivindicacoes.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	public void emitirImagens(){
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("patente_id", this.patente.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/imagens.jasper",
				this.response, parametros, "Imagens.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	@NotNull
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patente getPatente() {
		return patente;
	}

	public void setPatente(Patente patente) {
		this.patente = patente;
	}

	
}
