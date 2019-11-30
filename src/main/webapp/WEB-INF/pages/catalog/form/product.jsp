<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="catalog" method="POST" modelAttribute="product">

 	<div class="title-catalog">
		<b><spring:message code="label.product.title"/></b>
		<hr>			
	</div>	
   	<jsp:include page="/WEB-INF/pages/section/catalog/formHeader.jsp"/>
    <div class="secciones row">    
    	<div class="col-12 col-md-12 mt-2">    
	    	<h6 class="titulos mt-1">
	    		<form:label path="value"><spring:message code="label.product.pnumber"/></form:label>
	    	</h6>
	        <form:input path="value" type="text" class="form-control" maxlength="40" />
        </div>
    </div>
    
    <div class="secciones row">
    	<div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="description"><spring:message code="label.product.pdescription"/></form:label>
	        </h6>
	        <form:input path="description" type="text" class="form-control" maxlength="40" />
        </div>
    </div>
    
    <div class="secciones row">
    	<div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="airCraft"><spring:message code="label.product.aircraft"/></form:label>
	        </h6>
	        <form:input path="airCraft" type="text" class="form-control" maxlength="80" />
        </div>
    </div>    
    
    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="priceUSD"><spring:message code="label.product.priceusd"/></form:label>	
        	</h6>
			<form:input path="priceUSD" type="text" class="form-control qty" maxlength="12" />
        </div>        
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<form:label path="priceMXN"><spring:message code="label.product.pricemxn"/></form:label>	
			</h6>
			<form:input path="priceMXN" type="text" class="form-control qty" maxlength="12" />
       </div>
    </div>
    
    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="costUSD"><spring:message code="label.product.costusd"/></form:label>	
        	</h6>
			<form:input path="costUSD" type="text" class="form-control qty" maxlength="12" />
        </div>        
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="costMXN"><spring:message code="label.product.costmxn"/></form:label>	
        	</h6>
			<form:input path="costMXN" type="text" class="form-control qty" maxlength="12" />
       </div>
    </div>     
    
    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<form:label path="stockMin"><spring:message code="label.product.stockmin"/></form:label>	
			</h6>
			<form:input path="stockMin" type="text" class="form-control qty" maxlength="6"  />
        </div>        
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="stockMax"><spring:message code="label.product.stockmax"/></form:label>
        	</h6>
			<form:input path="stockMax" type="text" class="form-control qty" maxlength="6" />
       </div>
    </div>    

    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<label><spring:message code="label.product.unitmeasurement"/></label>
			</h6>
				<form:input path="unit" type="text" class="form-control" maxlength="20" />
        </div>        
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<label><spring:message code="label.product.bin"/></label>
			</h6>
				<form:input path="bin" type="text" class="form-control" maxlength="20" />
        </div>  
    </div>  
    
    <div class="secciones row">
      <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<label><spring:message code="label.product.expiredDate"/></label>
        	</h6>
        	<div class="form-inline">
				<form:input path="valueExpired" type="text" class="form-control middle qty" maxlength="4" />  
                <form:select path="timeExpired" class="middle" data-pk="${product.timeExpired}">
                  <option value="D"><spring:message code="label.days"/></option>
                  <option value="M"><spring:message code="label.months"/></option>
                  <option value="Y"><spring:message code="label.years"/></option>
                </form:select>
	         </div>       
       </div>
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<label><spring:message code="label.product.rotable"/></label>
			</h6>
            <div class="form-inline">
                <form:select path="rotableSerializable" data-pk="${product.rotableSerializable}">
                  <option value="false"><spring:message code="label.notApply"/></option>
                  <option value="true"><spring:message code="label.apply"/></option>
                </form:select>
	        </div>
        </div>        

    </div>     
      
	<jsp:include page="/WEB-INF/pages/section/catalog/formAction.jsp"/>

</form:form>


<script>
	
	$(document).ready(function(){			
		
		//Selected timeExpired
		var pkTimeExpired = $("#timeExpired").attr('data-pk');
		$('#timeExpired option[value='+pkTimeExpired+']').attr('selected', 'selected');

		//Selected rotable | serializable
		var pkRotableSerializable = $("#rotableSerializable").attr('data-pk');
		$('#rotableSerializable option[value='+pkRotableSerializable+']').attr('selected', 'selected');
		
	    $( '#priceUSD').blur(function() {
			var changeParam = 20;
			var priceUSD = $(this).val();
			var priceMXN = (priceUSD * changeParam);
			$( '#priceMXN').val(numeral(priceMXN).format('0.00'));
	    });	

	    $( '#costUSD').blur(function() {
			var changeParam = 20;
			var costUSD = $(this).val();
			var costMXN = (costUSD * changeParam);
			$( '#costMXN').val(numeral(costMXN).format('0.00'));
	    });			    
	        
	});	
	
</script>	
<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->
