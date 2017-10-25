package br.edu.univasf.patentes.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.model.StatusPatente;
import br.edu.univasf.patentes.repository.Patentes;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class FinalizacaoPatenteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Patentes patentes;
	
	@Transactional
	public Patente finalizar(Patente patente) throws NegocioException{
		patente = this.patentes.porId(patente.getId());
		
		if (patente.isNaoFinalizavel()) {
			throw new NegocioException("Patente não pode ser finalizada com status "
					+ patente.getStatus().getDescricao() + ".");
		}
				
		patente.setStatus(StatusPatente.FINALIZADA);
		
		patente = this.patentes.guardar(patente);
		
		return patente;
	}

}
