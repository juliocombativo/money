package com.jorgepalacio.money.tests.utils;

import java.io.Serializable;
import java.util.List;

import com.jorgepalacio.money.dao.DaoTemplate;
import com.jorgepalacio.money.dao.PagedResult;

public class MapDaoTemplate<T> implements DaoTemplate<T> {

	public void add(T t) {
		// TODO Auto-generated method stub

	}

	public List<T> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public T byId(Serializable id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	public PagedResult<T> pagedByNamedQuery(int pageSize, String query,
			Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public PagedResult<T> pagedByParams(int pageSize, String query,
			Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(T t) {
		// TODO Auto-generated method stub

	}

}
