package br.edu.univasf.patentes.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univasf.patentes.util.jsf.FacesUtil;

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.service.FinalizacaoPatenteService;
import br.edu.univasf.patentes.service.NegocioException;

@Named
@RequestScoped
public class FinalizacaoPatenteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FinalizacaoPatenteService finalizacaoPatenteService;
	
	@Inject
	private Event<PatenteAlteradaEvent> patenteAlteradaEvent;
	
	@Inject
	@PatenteEdicao
	private Patente patente;
	
	public void finalizarPatente(){
		try{
			System.out.println(this.patente.getId());
			this.patente = finalizacaoPatenteService.finalizar(this.patente);
			this.patenteAlteradaEvent.fire(new PatenteAlteradaEvent(this.patente));
			FacesUtil.addInfoMessage("Patente Finalizada com Sucesso!");
		}catch (NegocioException  ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}	
	
}
