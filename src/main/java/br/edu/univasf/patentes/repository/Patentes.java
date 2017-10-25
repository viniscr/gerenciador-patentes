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

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.repository.filter.PatenteFilter;
import br.edu.univasf.patentes.service.NegocioException;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class Patentes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Patente guardar(Patente patente) {
		return manager.merge(patente);
	}
	
	@Transactional
	public void removerPatente(Patente patente){
		try{
			patente = porId(patente.getId());
			manager.remove(patente);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Patente não pode ser excluída.");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Patente> filtrados(PatenteFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Patente.class);
		
		if (filtro.getId() != null) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		
		if(filtro.getUsuario() != null){
			criteria.add(Restrictions.eq("usuario", filtro.getUsuario()));
		}
		
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("titulo")).list();
	}

	public Patente porId(Long id) {
		return this.manager.find(Patente.class, id);
	}

	public List<Patente> porNome(String titulo) {
		return this.manager.createQuery("from Patente where upper(titulo) like :titulo", Patente.class)
				.setParameter("titulo", titulo.toUpperCase() + "%").getResultList();
	}
	
	public List<Patente> porUsuario(Long usuario_id) {
		return this.manager.createQuery("from Patente where usuario_id = :usuario_id", Patente.class)
				.setParameter("usuario_id", usuario_id.longValue()  + "%").getResultList();
	}
	
}

