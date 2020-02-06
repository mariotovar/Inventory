<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- -------------------------------------------------------------- -->
<!-- Checkout section -->
<!-- -------------------------------------------------------------- -->

<div id="checkout" class="section-wide">

	<form:form class="catalog order" method="POST" modelAttribute="quoteOrder">

		<div class="title-catalog">
			<b> <spring:message code="label.quote.title" />
			</b>
			<hr>
		</div>

		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<b> <spring:message code="label.quote.date" />
				</b>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<fmt:formatDate value="${quoteOrder.quoteDate}"
					pattern="dd-MM-yyyy HH:mm" />
			</div>
		</div>
		<hr />
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.client.title"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>
					${quoteOrder.client.value}
				</h6>
	       </div>
	    </div>
	    <hr />		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.client.email"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>
					${quoteOrder.client.email}
				</h6>
	       </div>
	    </div>
	    <hr />	
		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b class="info-total"> <spring:message code="label.total.subtotal" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b> 
						<span class="currency">${quoteOrder.totalMXN}</span>&nbsp;MXN
					</b>
				</h6>
			</div>
		</div>

	<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b class="info-total"> <spring:message code="label.total.iva" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b> 
						<span class="currency">${quoteOrder.factorIva*quoteOrder.totalMXN}</span>&nbsp;MXN
					</b>
				</h6>
			</div>
		</div>

	<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b class="info-total"> <spring:message code="label.total.order" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b> 
						<span class="currency">${(quoteOrder.factorIva*quoteOrder.totalMXN)+quoteOrder.totalMXN}</span>&nbsp;MXN
					</b>
				</h6>
			</div>
		</div>

		<div class="table-responsive table-items">
			<table
				class="table table-sm text-center table-striped table-hover edit">
				<tr class="primary">
					<th class="text-left">
						<spring:message	code="label.product.pnumber" />
					</th>
					<th class="text-left">
						<spring:message	code="label.product.pdescription" />
					</th>
	                <th class="text-left">
	                 	<spring:message code="label.product.condition"/>
	                 </th>					
					<th class="text-center">
						<spring:message code="label.cart.qty" />
					</th>												
					<th class="text-right">
						<spring:message	code="label.cart.unit.mxn" />
					</th>
					<th class="text-right">
						<spring:message	code="label.cart.total.mxn" />
					</th>
				</tr>
				<c:forEach items="${quoteOrder.items}" var="item"
					varStatus="status">
					<tr>
						<td class="text-left">
							${item.value}
						</td>
						<td class="text-left">
							${item.description}
						</td>
						<td class="text-left">
							${item.condition.key}
						</td>
						<td>
							${item.qty}
						</td>
						<td class="text-right">
							<span class="currency">${item.costMXN}	</span>
						</td>
						<td class="text-right">
							<span class="currency">${item.subCostMXN}</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<hr />
		
	    <c:if test="${not empty termsConditions}">
	    	<spring:message code="label.config.TERMS_CONDITIONS" />:<br />
	    	<span class="notes">
	    		${termsConditions}
	    	</span>	    		    		
	    </c:if>		

		<div class="actions">
			<div class="text-right buttons">
				<button type="button" class="btn btn-info btn-sm back">
					<span><i class="fas fa-times"></i></span>
					<spring:message code="label.back" />
				</button>
				<button type="button" class="order-submit btn btn-success btn-sm">
					&nbsp;
					<spring:message code="label.quote.confirm" />
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
