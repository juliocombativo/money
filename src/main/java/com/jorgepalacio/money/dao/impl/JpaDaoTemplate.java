package com.jorgepalacio.money.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaTemplate;

import com.jorgepalacio.money.dao.DaoTemplate;
import com.jorgepalacio.money.dao.PagedResult;

public class JpaDaoTemplate<T> implements DaoTemplate<T> {
	JpaTemplate template;
	Class<T> klazz;
	
	public void setKlazz(Class<T> klazz) {
		this.klazz = klazz;
	}
	
	@PersistenceContext
	public void setManager(EntityManager manager) {
		this.template = new JpaTemplate(manager);
	}
	
	public void add(T t) {
		template.persist(t);
	}
	
	public void edit(T t) {
		template.merge(t);
	}
	
	public void remove(T t) {
		template.remove(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> all() {
		return template.find("SELECT m FROM " + klazz.getSimpleName() + " m");
	}
	
	public T byId(Serializable id) {
		return template.find(klazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> byParams(String query, Object... params) {
		return template.find(query, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> byNamedQuery(String query, Object... params) {
		return template.findByNamedQuery(query, params);
	}
	
	public PagedResult<T> pagedByNamedQuery(int pageSize, String query, Object... params) {
		Query queryObject = template.getEntityManager().createNamedQuery(query);
		
		int order = 1;
		for(Object param : params) {
			queryObject.setParameter(order++, param);
		}
		
		return new PrivatePagedResult<T>(queryObject, pageSize);
	}
	
	public PagedResult<T> pagedByParams(int pageSize, String query, Object... params) {
		Query queryObject = template.getEntityManager().createQuery(query);
		
		int order = 1;
		for(Object param : params) {
			queryObject.setParameter(order++, param);
		}
		
		return new PrivatePagedResult<T>(queryObject, pageSize);
	}
	
	private static class PrivatePagedResult<T> implements PagedResult<T> {
		private Query query;
		private int pageSize;
		
		public PrivatePagedResult(Query query, int pageSize) {
			this.query = query;
			this.pageSize = pageSize;
		}
		
		@SuppressWarnings("unchecked")
		public List<T> firstPage() {
			query.setMaxResults(pageSize);
			return query.getResultList();
		}
		
		@SuppressWarnings("unchecked")
		public List<T> page(int page) {
			query.setMaxResults(pageSize);
			query.setFirstResult(pageSize * page);
			return query.getResultList();
		}
	}
}
