package br.edu.univasf.patentes.controller;

import br.edu.univasf.patentes.model.Patente;

public class PatenteAlteradaEvent {
	private Patente patente;
	
	public PatenteAlteradaEvent(Patente patente) {
		this.patente = patente;
	}

	public Patente getPatente() {
		return patente;
	}
}
