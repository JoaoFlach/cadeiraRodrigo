package edu.ifrs.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.ifrs.jpa.AbstractFacade;
import edu.ifrs.jpa.Disciplina;

@Stateless
public class DAO extends AbstractFacade<Disciplina>{
	
	@PersistenceContext(unitName="JPA")
	private EntityManager em;
	
	public DAO() {
		super(Disciplina.class);
	}

	@Override
	protected EntityManager getEntityManager() {		
		return em;
	}	
	
}
