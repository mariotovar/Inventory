<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- -------------------------------------------------------------- -->
<!-- Checkout section -->
<!-- -------------------------------------------------------------- -->

<div id="checkout" class="total-section">

	<form:form class="total-section" method="POST" modelAttribute="purchaseOrder">

		<div class="title-catalog">
			<b> <spring:message code="label.purchaseord.title" />
			</b>
			<hr>
		</div>

		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<b> <spring:message code="label.purchaseord.date" />
				</b>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<fmt:formatDate value="${purchaseOrder.purchaseDate}"
					pattern="dd-MM-yyyy HH:mm" />
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b> <spring:message code="label.provider.title" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6>${purchaseOrder.provider.value}</h6>
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b> <spring:message code="label.provider.email" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6>${purchaseOrder.provider.email}</h6>
			</div>
		</div>
		<c:if test="${not empty purchaseOrder.provider.emailCC1}">
			<div class="row">
				<div class="col-12 col-md-3 mt-2">
					<h6>
						<b> <spring:message code="label.provider.emailCC" /> (1)
						</b>
					</h6>
				</div>
				<div class="col-12 col-md-9 mt-2">
					<h6>${purchaseOrder.provider.emailCC1}</h6>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty purchaseOrder.provider.emailCC2}">
			<div class="row">
				<div class="col-12 col-md-3 mt-2">
					<h6>
						<b> <spring:message code="label.provider.emailCC" /> (2)
						</b>
					</h6>
				</div>
				<div class="col-12 col-md-9 mt-2">
					<h6>${purchaseOrder.provider.emailCC2}</h6>
				</div>
			</div>
		</c:if>
		<hr />
		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b>
						<spring:message code="label.shipto.title" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6>
					${purchaseOrder.shipto.value} | 
					${purchaseOrder.shipto.address}, ${purchaseOrder.shipto.zip}, ${purchaseOrder.shipto.state}, ${purchaseOrder.shipto.country}
				</h6>
			</div>
		</div>
		<hr />		
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
						<span class="currency">${purchaseOrder.totalUSD}</span>&nbsp;USD
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
						<spring:message	code="label.cart.unit.usd" />
					</th>
					<th class="text-right">
						<spring:message	code="label.cart.total.usd" />
					</th>
	                <th width="30%" class="text-center">
		                <spring:message code="label.cart.coreValue"/>
	                </th>		                 		
				</tr>
				<c:forEach items="${purchaseOrder.items}" var="item"
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
							<span class="currency">${item.priceUSD}	</span>
						</td>
						<td class="text-right">
							<span class="currency">${item.subtotalUSD}</span>
						</td>
						<td>
							${item.coreValue}
						</td>						
					</tr>
				</c:forEach>
			</table>
		</div>
		<hr />

	    <c:if test="${not empty purchaseOrder.notes}">
	    	<span class="notes">
	    		*** ${purchaseOrder.notes}
	    	</span>
	    </c:if>

		<div class="actions">
			<div class="text-right buttons">
				<!-- 
				<div class="text-right check-mail">
					<form:checkbox path="emailing" checked="checked" />
					<spring:message code="label.purchaseord.send.mail" />
				</div>
				<br />
				 -->
				<button type="button" class="btn btn-info btn-sm back">
					<span><i class="fas fa-times"></i></span>
					<spring:message code="label.back" />
				</button>
				<button type="button" class="order-submit btn btn-success btn-sm">
					&nbsp;
					<spring:message code="label.purchaseord.confirm" />
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
