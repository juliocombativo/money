package com.jorgepalacio.money.tests.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import com.jorgepalacio.money.dao.PagedResult;
import com.jorgepalacio.money.dao.impl.JpaDaoTemplate;

public class MapDaoTemplate<T> extends JpaDaoTemplate<T> {
	private Map<Serializable, T> contents = new LinkedHashMap<Serializable, T>();
	private IdRecover<T> idRecover;
	
	private class IdRecover<Q> {
		private Field idField;
		
		public IdRecover(Class<Q> klazz) {
			for(Field field : klazz.getDeclaredFields()) {
				if(field.isAnnotationPresent(Id.class))
					idField = field;
			}
		}
		
		public Serializable id(Q t) {
			try {
				return (Serializable) idField.get(t);
			} catch(IllegalAccessException iae) {
				return null;
			}
		}
		
		public void id(Q t, Serializable id) {
			try {
				idField.setAccessible(true);
				idField.set(t, id);
			} catch(IllegalAccessException iae) { iae.printStackTrace(); }
		}
	}
	
	public MapDaoTemplate(Class<T> klazz) {
		idRecover = new IdRecover<T>(klazz);
	}
	
	public void add(T t) {
		idRecover.id(t, contents.size() + 1);
		contents.put(idRecover.id(t), t);
	}

	public List<T> all() {
		return new ArrayList<T>(contents.values());
	}

	public T byId(Serializable id) {
		return contents.get(id);
	}

	public List<T> byNamedQuery(String query, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> byParams(String query, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public void edit(T t) {
		if(!contents.containsKey(idRecover.id(t)))
			throw new IllegalStateException("Element is not present");
		contents.put(idRecover.id(t), t);
	}

	public PagedResult<T> pagedByNamedQuery(int pageSize, String query, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public PagedResult<T> pagedByParams(int pageSize, String query, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(T t) {
		contents.remove(idRecover.id(t));
	}

}
