package com.jorgepalacio.money.tests.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class SetupConfiguration {
	public static void load() {
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		try {
			DataSource ds = new DriverManagerDataSource("jdbc:derby:memory:kakaw");
			builder.bind("jdbc/kakawDS", ds);
			
			builder.activate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
