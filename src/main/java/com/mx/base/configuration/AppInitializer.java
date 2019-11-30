package com.mx.base.configuration;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
    @Override
    protected Filter[] getServletFilters() {
       CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
       encodingFilter.setForceEncoding(true);
       encodingFilter.setEncoding("ISO-8859-1");
       return new Filter[]{
    		   				encodingFilter,
    		   				new DelegatingFilterProxy("springSecurityFilterChain"),
    		   				new OpenEntityManagerInViewFilter()};

    }    
    
	//Return to custom error page
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	    boolean done = registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	    if(!done) throw new RuntimeException();
	    
	 // upload temp file will put here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(), maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
        registration.setMultipartConfig(multipartConfigElement);
	}
    
}