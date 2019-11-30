package com.mx.base.controllers.catalog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.HandleCatalog;

public class CatalogHandlerResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(CatalogModel.class);
    }

    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
    	
    	//Change this lines, choose another API
    	HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();   
    	int lastIndex = request.getRequestURI().lastIndexOf("/") + 1;
    	String beanName = request.getRequestURI().substring(lastIndex);
        
    	WebDataBinder webDataBinder = webDataBinderFactory.createBinder(nativeWebRequest, HandleCatalog.newInstance(beanName), beanName);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues(nativeWebRequest.getParameterMap());
        webDataBinder.bind(mutablePropertyValues);        
        return webDataBinder.getBindingResult().getTarget();
        
    }
    
}
