package com.jorgepalacio.money.dao;

import java.io.Serializable;
import java.util.List;

public interface DaoTemplate<T> {
	public void add(T t);
	
	public void edit(T t);
	
	public void remove(T t);
	
	public T byId(Serializable id);
	
	public List<T> all();
	
	public List<T> byParams(String query, Object... params);
	
	public List<T> byNamedQuery(String query, Object... params);
	
	public PagedResult<T> pagedByParams(int pageSize, String query, Object... params);
	
	public PagedResult<T> pagedByNamedQuery(int pageSize, String query, Object... params);
}
