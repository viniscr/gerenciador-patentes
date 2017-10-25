package br.edu.univasf.patentes.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.univasf.patentes.model.Grupo;
import br.edu.univasf.patentes.repository.filter.GrupoFilter;
import br.edu.univasf.patentes.service.NegocioException;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class Grupos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Grupo guardar(Grupo grupo) {
		return manager.merge(grupo);
	}
	
	@Transactional
	public void removerGrupo(Grupo grupo){
		try{
			grupo = porId(grupo.getId());
			manager.remove(grupo);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Grupo não pode ser excluído.");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Grupo> filtrados(GrupoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Grupo.class);
		
		if (filtro.getId() != null) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("titulo", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("titulo")).list();
	}

	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}

	public Grupo porNome(String nome) {
		return this.manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
				.setParameter("nome", nome.toUpperCase()).getSingleResult();
	}
	
}
