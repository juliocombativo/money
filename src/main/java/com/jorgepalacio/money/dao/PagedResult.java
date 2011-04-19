package com.jorgepalacio.money.dao;

import java.util.List;

public interface PagedResult<T> {
	public List<T> firstPage();
	
	public List<T> page(int page);
}
