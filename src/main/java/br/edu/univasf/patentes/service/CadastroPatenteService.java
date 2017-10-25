package br.edu.univasf.patentes.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.repository.Patentes;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class CadastroPatenteService implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Inject
	private Patentes patentes;
	
	@Transactional
	public Patente salvar(Patente patente){
		
		return patentes.guardar(patente);
	}

	
}
