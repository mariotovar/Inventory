<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- -------------------------------------------------------------- -->
<!-- Checkout section -->
<!-- -------------------------------------------------------------- -->

<div id="shippingCost" class="section">

	<form:form class="order" method="POST" modelAttribute="saleOrder">

		<div class="title-catalog">
			<b>
				<spring:message code="label.saleord.title"/>
			</b>
			<hr>
		</div>

	    <div class="secciones row">
	        <div class="col-12 col-md-4 mt-2">
	        	<h6 class="titulos mt-1">
	        		+ <spring:message code="label.saleord.shipping"/> (MXN)	
	        	</h6>
				<form:input path="shippingCostMXN" type="text" class="form-control qty" size="10" maxlength="10" />							
	        </div>        
	        <div class="col-12 col-md-8 mt-2">
	       </div>
	    </div>

		<form:radiobutton path="charge" value="true"/>
			<spring:message code="label.saleord.charge.customer"/>
		<form:radiobutton path="charge" value="false" />
			<spring:message code="label.saleord.expense"/>

	    <div class="secciones row">
	    	<div class="col-12 col-md-12 mt-2">
		        <h6 class="titulos mt-1">
		        	<form:label path="notes">
		        		<spring:message code="label.cart.notes" />
		        	</form:label>
		        </h6>
		        <form:textarea path="notes" rows="5" cols="30" class="md-textarea form-control" />
	        </div>
	    </div>	

		<div class="actions">
			<div class="text-right buttons">
				<button type="button" class="btn btn-info btn-sm back">
					<span><i class="fas fa-times"></i></span>
					<spring:message code="label.back" />
				</button>
				<button type="button" class="order-submit btn btn-success btn-sm">
					&nbsp;
					<spring:message code="label.continue" />
					<span><i class="fas fa-chevron-circle-right"></i></span>
				</button>
			</div>
		</div>
		
	</form:form>

</div>

<!-- -------------------------------------------------------------- -->
<script>
	$(".order-submit").click(function(e) {
		e.preventDefault();
		var form = $(this).closest('form');
		$('#loader').modal('show');
		form.submit();
		return true;
	});
</script>
<!-- -------------------------------------------------------------- -->
