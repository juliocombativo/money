package com.jorgepalacio.money.tests.utils;

import java.lang.reflect.Field;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class SamePersistentFields<T> extends TypeSafeMatcher<T> {
	private T t;
	
	protected SamePersistentFields(T t) {
		this.t = t;
	}
	
	@SuppressWarnings("unchecked")
	public boolean matchesSafely(T item) {
		Class<T> klazz = (Class<T>) t.getClass();
		for(Field field : klazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(Transient.class) || field.isAnnotationPresent(Id.class))
				continue;
			
			boolean matches;
			try {
				field.setAccessible(true);
				matches = field.get(t).equals(field.get(item));
				if(!matches) {
					return false;
				}
			} catch (IllegalArgumentException e) {
				return false;
			} catch (IllegalAccessException e) {
				return false;
			}
		}
		return true;
	}
	
	public void describeTo(Description description) {
		description.appendText("has not the same persistent fields");
	}
	
	@Factory
	public static <T> Matcher<T> hasSameFieldsThat(T t) {
		return new SamePersistentFields<T>(t);
	}
}
