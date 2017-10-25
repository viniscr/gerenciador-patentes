package br.edu.univasf.patentes.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.univasf.patentes.model.Imagem;
import br.edu.univasf.patentes.service.NegocioException;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class Imagens implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Imagem guardar(Imagem imagem){
		return manager.merge(imagem);
	}
	
	@Transactional
	public void removerImagem(Imagem imagem){
		try{
			imagem = porId(imagem.getId());
			manager.remove(imagem);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Imagem não pode ser excluída.");
		}
	}
	
	public Imagem porId(Long id){
		return manager.find(Imagem.class, id);
	}
	
	public List<Imagem> porPatente(Long patente_id) {
		return this.manager.createQuery("from Imagem where patente_id = :patente_id", Imagem.class)
				.setParameter("patente_id", patente_id.longValue() + "%").getResultList();
	}

}
