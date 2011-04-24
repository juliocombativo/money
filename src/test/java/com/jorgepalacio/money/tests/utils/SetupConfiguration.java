package com.jorgepalacio.money.tests.utils;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class SetupConfiguration {
	private static ApplicationContext context;
	
	public static void load(String... xmlFiles) {
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		try {
			DataSource ds = new DriverManagerDataSource("jdbc:derby:memory:kakaw");
			builder.bind("jdbc/kakawDS", ds);
			
			builder.activate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		context = new ClassPathXmlApplicationContext(xmlFiles);
	}
	
	public static void inject(Object o) {
		context.getAutowireCapableBeanFactory().autowireBean(o);
	}
}
