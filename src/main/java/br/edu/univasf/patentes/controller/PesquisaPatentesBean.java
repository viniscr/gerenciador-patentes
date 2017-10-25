package br.edu.univasf.patentes.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.edu.univasf.patentes.service.NegocioException;
import br.edu.univasf.patentes.util.jsf.FacesUtil;

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.repository.Patentes;
import br.edu.univasf.patentes.repository.filter.PatenteFilter;
import br.edu.univasf.patentes.security.UsuarioSistema;

@Named
@ViewScoped
public class PesquisaPatentesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Patentes patentes;
	
	private PatenteFilter filtro;
	private List<Patente> patentesFiltradas;
	
	private Patente patenteSelecionada;
	
	@PostConstruct
	private void init(){
		filtro = new PatenteFilter();
		filtro.setUsuario(getUsuarioLogado().getUsuario());
		pesquisar();
	}
	
	public PesquisaPatentesBean(){
		filtro = new PatenteFilter();
		filtro.setUsuario(getUsuarioLogado().getUsuario());
	}
	
	public void excluir() {
		try {
			patentes.removerPatente(patenteSelecionada);
			patentesFiltradas.remove(patenteSelecionada);
			
			FacesUtil.addInfoMessage("Patente " + patenteSelecionada.getTitulo() 
					+ " excluída com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar(){
		patentesFiltradas = patentes.filtrados(filtro);
	}

	public List<Patente> getPatentesFiltradas() {
		return patentesFiltradas;
	}

	public PatenteFilter getFiltro() {
		return filtro;
	}

	public Patente getPatenteSelecionada() {
		return patenteSelecionada;
	}

	public void setPatenteSelecionada(Patente patenteSelecionada) {
		this.patenteSelecionada = patenteSelecionada;
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
				
		return usuario;
	}
	
	
}