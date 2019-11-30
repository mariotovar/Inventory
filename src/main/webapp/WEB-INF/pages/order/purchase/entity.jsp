<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    	
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Entity section -->
 	<!-- -------------------------------------------------------------- -->
          
    <form:form class="order" method="POST" modelAttribute="purchaseOrder">

	 	<div class="title-catalog">
			<b>
				<spring:message code="label.purchaseord.title"/>
			</b>
			<hr>			
		</div>	
		
		<div>
	        <h6 class="titulos mt-1">
	        	<form:label path="provider.pk">
	        		<spring:message code="label.purchaseord.select.provider"/>
	        	</form:label>
	        </h6>
	    	<form:input path="provider.pk" type="hidden" />
	    	<input type="text" class="ui-autocomplete-input form-control" data-beanName="provider" />
		</div>

	     <div class="buttons text-right">
			<a href="${pageContext.request.contextPath}/view/lst/purchaseOrder" class="btn btn-info btn-sm">
	      		<span><i class="fas fa-times"></i></span> 
     				<spring:message code="label.back"/>
	      	</a>	
	        <button type="button" class="btn btn-secondary btn-sm">
	        	&nbsp;<spring:message code="label.continue"/>
	        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
	        </button>	        
	     </div>      

		<script>
			//Enable continue button over provider section
			function overwriteSelectAutocomplete(key, value){
				$('#provider\\.pk').val(key);
				$('.btn-secondary').removeClass('btn-secondary').addClass('btn-primary');
				$('.btn-primary').unbind( "click" );
				$('.btn-primary').click(function() {
					$('#loader').modal('show');
					$("#purchaseOrder").submit();
					return true;
				});						
			}	
		</script>

    </form:form>
     
 	<!-- -------------------------------------------------------------- -->
 	<!-- -------------------------------------------------------------- -->