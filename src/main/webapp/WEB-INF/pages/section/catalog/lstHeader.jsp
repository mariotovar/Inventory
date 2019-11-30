<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<div class="secciones row">
    <div class="col-12 col-md-6 mt-2">
    	<!-- 
       <input type="text" class="ui-autocomplete-input form-control" placeHolder=" >> Search ${beanName}." data-beanName="product" />
     	-->
    </div>        
    <div class="col-12 col-md-6 mt-2 text-right">
      <a href="${pageContext.request.contextPath}/catalog/form/${beanName}" class="btn btn-outline-prin m-2">
      	<span><i class="fas fa-plus"></i></span> 
      		<spring:message code="button.add"/>
      </a>
   </div>
</div> 