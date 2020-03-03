<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- -------------------------------------------------------------- -->
<!-- Checkout section -->
<!-- -------------------------------------------------------------- -->

<div id="checkout" class="section">

	<form:form class="catalog order" method="POST"
		modelAttribute="purchaseOrder">

		<div class="title-catalog">
			<b> <spring:message code="label.purchaseord.title" />
			</b>
			<hr>
		</div>
		<div class="secciones row">
			<div class="col-12 col-md-12 mt-2">
				<h6 class="titulos mt-1">
					<label> <spring:message code="label.shipto.title" />
					</label>
				</h6>
				<form:select id="shiptoPK" path="shipto.pk"
					data-pk="${purchaseOrder.shipto.pk}">
					<c:forEach items="${lstShipto}" var="shipto">
						<option value="${shipto.pk}">${shipto.value} |
							${shipto.address}, ${shipto.zip}, ${shipto.state},
							${shipto.country}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>

		<div class="secciones row">
			<div class="col-12 col-md-12 mt-2">
				<h6 class="titulos mt-1">
					<form:label path="notes">
						<spring:message code="label.cart.notes" />
					</form:label>
				</h6>
				<form:textarea path="notes" rows="5" cols="30"
					class="md-textarea form-control" />
			</div>
		</div>


		<div class="actions">
			<div class="text-right buttons">
				<div class="text-right check-mail"></div>
				<br />
				<button type="button" class="btn btn-info btn-sm back">
					<span><i class="fas fa-times"></i></span>
					<spring:message code="label.back" />
				</button>
				<button type="button" class="order-submit btn btn-primary btn-sm">
					&nbsp;
					<spring:message code="label.continue" />
					<span><i class="fas fa-chevron-circle-right"></i></span>
				</button>
			</div>
		</div>

	</form:form>

</div>

<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->
<script>
	$(document).ready(
			function() {

				//Selected Shipto
				var pkShipto = $("#shiptoPK").attr('data-pk');
				$('#shiptoPK option[value=' + pkShipto + ']').attr('selected',
						'selected');

				$(".order-submit").click(function(e) {
					e.preventDefault();
					var form = $(this).closest('form');
					$('#loader').modal('show');
					form.submit();
					return true;
				});

			});
</script>

<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->
