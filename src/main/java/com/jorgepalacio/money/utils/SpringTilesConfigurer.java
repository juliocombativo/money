package com.jorgepalacio.money.utils;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.context.AbstractTilesApplicationContextFactory;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.evaluator.el.ELAttributeEvaluator;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.factory.TilesContainerFactory;
import org.apache.tiles.preparer.BasicPreparerFactory;
import org.apache.tiles.servlet.context.ServletTilesApplicationContext;
import org.apache.tiles.servlet.context.wildcard.WildcardServletTilesApplicationContextFactory;
import org.apache.tiles.web.util.ServletContextAdapter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.tiles2.SpringLocaleResolver;

@SuppressWarnings("deprecation")
public class SpringTilesConfigurer implements ServletContextAware,
		InitializingBean, DisposableBean {
	private ServletContext context;
	private final Properties properties = new Properties();
	private TilesApplicationContext tilesContext;
	
	public SpringTilesConfigurer() {
		properties.put(AbstractTilesApplicationContextFactory.APPLICATION_CONTEXT_FACTORY_INIT_PARAM, 
				WildcardServletTilesApplicationContextFactory.class.getName());
		properties.put(TilesContainerFactory.PREPARER_FACTORY_INIT_PARAM, 
				BasicPreparerFactory.class.getName());
		properties.put(DefinitionsFactory.LOCALE_RESOLVER_IMPL_PROPERTY,
				SpringLocaleResolver.class.getName());
		properties.put(TilesContainerFactory.ATTRIBUTE_EVALUATOR_INIT_PARAM, ELAttributeEvaluator.class.getName());
		properties.put(TilesContainerFactory.CONTAINER_FACTORY_MUTABLE_INIT_PARAM, Boolean.FALSE.toString());
	}

	public void setDefinitions(String[] definitions) {
		if(definitions != null) {
			String defs = "";
			for(String definition : definitions) {
				defs += definition + (defs.length() > 0 ? "," : "");
			}
			properties.put(DefinitionsFactory.DEFINITIONS_CONFIG, defs);
		}
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	public void afterPropertiesSet() throws Exception {
		TilesContainer container = createContainer();
		TilesAccess.setContainer(tilesContext, container);
	}

	private TilesContainer createContainer() {
		ServletContextAdapter adapter = new ServletContextAdapter(new DelegatingServletConfig());
		TilesApplicationContext preContext = new ServletTilesApplicationContext(adapter);
		AbstractTilesApplicationContextFactory factory = AbstractTilesApplicationContextFactory.createFactory(preContext);
		tilesContext = factory.createApplicationContext(adapter);
		AbstractTilesContainerFactory containerFactory = AbstractTilesContainerFactory.getTilesContainerFactory(tilesContext);
		return containerFactory.createContainer(this.tilesContext);
	}

	public void destroy() throws Exception {
		TilesAccess.setContainer(tilesContext, null);
	}

	private class DelegatingServletConfig implements ServletConfig {
		public String getServletName() {
			return "TilesConfigurer";
		}
			
		public ServletContext getServletContext() {
			return context; 
		}
		
		public String getInitParameter(String name) {
			return properties.getProperty(name);
		}
		
		@SuppressWarnings("unchecked")
		public Enumeration getInitParameterNames() {
			return properties.keys();
		}
	}
}