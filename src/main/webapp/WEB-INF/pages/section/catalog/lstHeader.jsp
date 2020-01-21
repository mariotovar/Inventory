<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<div class="secciones row">
    <div class="col-12 col-md-9 mt-2">    	
   		<input type="text" class="ui-autocomplete-input form-control" placeHolder=" >> Search ${beanName}." data-beanName="${beanName}" />   
    </div>        
    <div class="col-12 col-md-3 mt-2 text-right">
	  <a href="${pageContext.request.contextPath}/catalog/page/${beanName}/1" class="btn btn-outline-prin m-2">
      	<span><i class="fas fa-list"></i></span> 
      	<spring:message code="button.list"/>
	  </a>   		      
      <a href="${pageContext.request.contextPath}/catalog/form/${beanName}" class="btn btn-outline-prin m-2">
      	<span><i class="fas fa-plus"></i></span> 
      	<spring:message code="button.add"/>
      </a>
   </div>
</div> 

<script>

	//Search product by name
	function overwriteSelectAutocomplete(key, value){ 		
		window.location.href = context()+"/catalog/single/${beanName}/"+key;
	}

</script>