package br.edu.univasf.patentes.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univasf.patentes.model.Universidade;

public class Universidades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Universidade porId(Long id){
		return manager.find(Universidade.class, id);
	}
	
	public List<Universidade> universidades(){
		return manager.createQuery("from Universidade", Universidade.class).getResultList();
	}

}
