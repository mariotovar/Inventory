package com.mx.base.util.velocity;

import java.io.StringWriter;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;

import com.mx.base.models.catalog.SaleOrder;

public class SaleOrderNoteTemplate {
	
	
	public static String getSaleOrderNote(SaleOrder saleOrder){

        VelocityEngine ve = new VelocityEngine();        
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        
        VelocityContext context = new VelocityContext();
        context.put("math", new MathTool());
        context.put("date", new DateTool());
        context.put("number", new NumberTool());
        context.put("currentDate", new Date());
        context.put("saleOrder", saleOrder);
        
        String template = "layouts/saleOrderNoteEmail.vm";
        Template t = ve.getTemplate(template);
        StringWriter writer = new StringWriter();
        t.merge( context, writer );

        return writer.toString();
		
		
	}
	

}	


