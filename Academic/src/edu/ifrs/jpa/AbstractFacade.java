/** 
 * Copyright 2014 IFRS - Instituto Federal de Educação, Ciência e Tecnologia do
 * Rio Grande do Sul
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package edu.ifrs.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 * @author Rodrigo Prestes Machado
 */
public abstract class AbstractFacade<T> {
    
    private Class<T> objClass;

    protected abstract EntityManager getEntityManager();

    
    public AbstractFacade(Class<T> objClass) {
        this.objClass = objClass;
    }
    
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(objClass, id);
    }

    public List<T> findAll() {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(objClass);
		
		Root<T> rootObjt = criteria.from(objClass);
        criteria.select(rootObjt);
        
		return getEntityManager().createQuery(criteria).getResultList();
    }

    public List<T> findRange(int[] range) {
    	
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(objClass);
		
		Root<T> rootObjt = criteria.from(objClass);
        criteria.select(rootObjt);
		
        Query query = getEntityManager().createQuery(criteria);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        
        return query.getResultList();
    }

    public Long count() {
    	CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
    	CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    	cq.select(qb.count(cq.from(objClass)));
    	//cq.where();
    	return getEntityManager().createQuery(cq).getSingleResult();
    }

	public List<T> findByName(String name, SingularAttribute<T, String> colum) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(objClass);
		
		Root<T> objRoot = criteria.from(objClass);
		criteria.where(builder.equal(objRoot.get(colum), name ) );
		
		return getEntityManager().createQuery( criteria ).getResultList();
	}
    
}
