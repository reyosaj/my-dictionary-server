package com.rsp.dictionary.config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.rsp.dictionary.config.cache.DataLoader;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final Logger logger = Logger
			.getLogger(WebAppInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		WebApplicationContext rootContext = createRootContext(servletContext);

		configureSpringMvc(servletContext, rootContext);
		
		configureDataLoader(servletContext);

	}
	
	private WebApplicationContext createRootContext(
			ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		rootContext.register(CoreConfig.class);
		rootContext.refresh();

		servletContext.addListener(new ContextLoaderListener(rootContext));
		servletContext.setInitParameter("defaultHtmlEscape", "true");

		return rootContext;
	}

	private void configureDataLoader(ServletContext servletContext) {
		servletContext.addListener(new DataLoader());
	}

	private void configureSpringMvc(ServletContext servletContext,
			WebApplicationContext rootContext) {
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(MvcConfig.class);

		mvcContext.setParent(rootContext);

		ServletRegistration.Dynamic appServlet = servletContext.addServlet(
				"webservice", new DispatcherServlet(mvcContext));
		appServlet.setLoadOnStartup(1);

		Set<String> mappingConflicts = appServlet.addMapping("/");

		if (!mappingConflicts.isEmpty()) {
			for (String s : mappingConflicts) {
				logger.error("Mapping Conflict : " + s);
			}
			throw new IllegalStateException(
					"'webservice' cannot be mapped to '/");
		}

	}

}
