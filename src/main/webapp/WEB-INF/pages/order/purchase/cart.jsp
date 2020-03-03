<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!-- -------------------------------------------------------------- -->
<!-- Entity section -->
<!-- -------------------------------------------------------------- -->

<div id="cart" class="wide-section">

	<form:form class="wide-section" method="POST"
		modelAttribute="purchaseOrder">

		<div class="title-catalog">
			<b> <spring:message code="label.purchaseord.title" />
			</b>
			<hr>
		</div>

		<br />

		<p class="title-catalog">
			<b><span class="total">$0.00</span>&nbsp;USD</b>
		</p>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<h6 class="titulos mt-1">
						<form:label path="items">
							<spring:message code="label.cart.select" />
						</form:label>
					</h6>
				</div>
				<div class="col-md-3">
					<h6 class="titulos mt-1">
						<form:label path="items">
									<spring:message code="label.config.FACTOR_CONVERTION" />
						</form:label>
					</h6>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<input type="text" class="ui-autocomplete-input form-control"
						data-beanName="product" />
				</div>
				<div class="col-md-3">
				  <form:input path="factorConversion" class="md-textarea form-control text-right qtyFactor" />
				</div>
			</div>
		</div>
		<c:forEach items="${conditions}" var="condition">
			<input type="hidden" value="${condition.key}" data-pk="${condition}"
				class="condition">
		</c:forEach>

		<div class="table-responsive table-items">
			<table
				class="table table-sm text-center table-striped table-hover edit">
				<tr class="primary">
					<th class="text-left"><spring:message
							code="label.product.pnumber" /></th>
					<th class="text-left"><spring:message
							code="label.product.pdescription" /></th>
					<th class="text-left"><spring:message
							code="label.product.condition" /></th>
					<th class="text-center"><spring:message code="label.cart.qty" />
					</th>
					<th class="text-right"><spring:message
							code="label.cart.unit.usd" /></th>
					<th class="text-right"><spring:message
							code="label.cart.total.usd" /></th>
					<th width="30%" class="text-center"><spring:message
							code="label.cart.coreValue" /></th>
				</tr>
			</table>
		</div>

		<div class="buttons text-right">
			<c:choose>
				<c:when test="${not empty purchaseOrder.pkPurchase}">
					<a href="${pageContext.request.contextPath}/view/lst/purchaseOrder"
						class="btn btn-info btn-sm"> <span><i
							class="fas fa-times"></i></span> <spring:message code="label.back" />
					</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/order/purchase/entity"
						class="btn btn-info btn-sm"> <span><i
							class="fas fa-times"></i></span> <spring:message code="label.back" />
					</a>
				</c:otherwise>
			</c:choose>
			<button type="button" class="btn btn-secondary btn-sm">
				&nbsp;
				<spring:message code="label.continue" />
				<span><i class="fas fa-chevron-circle-right"></i></span>
			</button>
		</div>

	</form:form>

</div>


<script>
	var index = -1;
	$(document).ready(function() {
		$.ajax({
			async : true,
			type : "GET",
			url : context() + "/order/purchase/items",
			dataType : 'json',
			success : function(response) {
				console.log("todo ok");
		
				$.each(response, function(index) {
					addCartRow(response[index]);
				});
			},
			error : function(xhr, ajaxOptions, thrownError,aa) {
			console.log("uno_dos_tres"+xhr+"ajaxOptions"+ajaxOptions+"thrownError"+thrownError);
				alert("xhr " + xhr);
				alert("ajaxOptions " + ajaxOptions);
				alert("thrownError " + thrownError);
			}
		});
	});

	//Add row on select by key
	function overwriteSelectAutocomplete(key, value) {
		$.ajax({
			//cache:true,
			async : true,
			type : "GET",
			url : "../../search/find/product/pk/" + key,
			dataType : 'json',
			success : function(response) {
				addCartRow(response.catalog);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("error");
			}
		});
	}

	//Add row to cart
	function addCartRow(catalog) {

		key = catalog.pk;
		$('tr:hidden').remove();

		//Add plus qyt for duplicate key
		var duplicate = false;
		/*
		$(".qty").each(function() {
			if($(this).attr('data-key')==key){
				duplicate = true;
				$(this).val(parseInt($(this).val())+1);
				calculateSubTotal($(this));
			}
		});
		 */

		if (!duplicate) {

			index++;
			var row = '';
			var qty = (catalog.qty == undefined) ? 1 : catalog.qty;
			var priceUSD = (catalog.priceUSD == undefined) ? 0
					: catalog.priceUSD;
			var condition = (catalog.condition == undefined) ? ''
					: catalog.condition;
			var coreValue = (catalog.coreValue == undefined) ? ''
					: catalog.coreValue;

			column = '';
			column += "<td class='text-left'>";
			column += "<button id='delete"+index+"' type='button' class='btn btn-sm btn-delete' data-index='"+index+"'>";
			column += "<span><i class='fas fa-times'></i></span>";
			column += "</button>";
			column += "<input name='items["+index+"].pk' type='hidden' value='"+catalog.pk+"' />";
			column += "<input name='items["+index+"].value' type='hidden' value='"+catalog.value+"' />";
			column += "<label>" + catalog.value + "</label>";
			column += "</td>";
			row += column;

			column = '';
			column += "<td class='text-left'>";
			column += "<input name='items["+index+"].description' type='hidden' value='"+catalog.description+"' />";
			column += "<label>" + catalog.description + "</label>";
			column += "</td>";
			row += column;

			column = '';
			column += "<td>";
			column += "<select id='condition"+index+"' name='items["+index+"].condition' class='form-control' data-pk='"+catalog.condition+"' data-index='"+index+"'>";
			$(".condition").each(
					function() {
						var pk = $(this).attr('data-pk');
						column += "<option value='"+pk+"'>" + $(this).val()
								+ "</option>";
					});
			column += "</select>";
			column += "</td>";
			row += column;

			column = '';
			column += "<td class='text-center'>";
			column += "<input id='qty"+index+"' name='items["+index+"].qty' class='qty form-control output' type='text' size='4' maxlength='6' value='"+qty+"' data-key='"+index+"' data-price='"+priceUSD+"' />";
			column += "</td>";
			row += column;

			column = '';
			column += "<td class='text-right'>";
			column += "<input id='priceUSD"+index+"' name='items["+index+"].priceUSD' class='number form-control' type='text' size='4' maxlength='6' value='"+priceUSD+"' data-key='"+index+"' />";
			column += "</td>";
			row += column;

			column = '';
			column += "<td class='text-right'>";
			column += "<label>";
			column += "<span id='subtotal"+index+"'>";
			column += formatCurrency(priceUSD * qty);
			column += "</span>";
			column += "</label>";
			column += "</td>";
			row += column;

			column = '';
			column += "<td class='text-right'>";
			column += "<input name='items["+index+"].coreValue' type='text' class='form-control' maxlength='40' value='"+coreValue+"' />";
			column += "</td>";
			row += column;

			row = '<tr>' + row + '</tr>';
			$('table> tbody:last').append(row);

			//Add delete event
			$("#delete" + index).click(function() {
				var _index = $(this).attr('data-index');
				$("#qty" + _index).val('0');
				$(this).closest('tr').hide();
				calculateTotal();
				enableContinue();
			});

			//Add condition event
			$("#condition" + index).change(
					function() {
						var _index = $(this).attr('data-index');
						if ($(this).val().includes("_EX")) {
							$("input[name='items[" + index + "].coreValue']")
									.prop("disabled", false);
						} else {
							$("input[name='items[" + index + "].coreValue']")
									.val('');
							$("input[name='items[" + index + "].coreValue']")
									.prop("disabled", true);
						}
					});

			//Add onblur qty event
			$("#qty" + index).blur(function() {
				checkNumber($(this));
				var _index = $(this).attr('data-key');
				calculateSubCost(_index);
				calculateCost();
				enableContinue();
			});

			$("#priceUSD" + index).blur(function() {
				checkNumber($(this))
				var _index = $(this).attr('data-key');
				calculateSubCost(_index);
				calculateCost();
				enableContinue();
			});

		}

		var condition = $('#condition' + index).attr('data-pk');
		$('#condition' + index + ' option[value=' + condition + ']').attr(
				'selected', 'selected');
		if ($('#condition' + index).val().includes("_EX")) {
			$("input[name='items[" + index + "].coreValue']").prop("disabled",
					false);
		} else {
			$("input[name='items[" + index + "].coreValue']").val('');
			$("input[name='items[" + index + "].coreValue']").prop("disabled",
					true);
		}

		//Add row
		calculateTotal();
		enableContinue();

	}

	function calculateSubCost(_index) {
		var subtotal = 0;
		var qty = $("#qty" + _index).val();
		var cost = $("#priceUSD" + _index).val();
		$("#subtotal" + _index).text(formatCurrency(qty * cost));
	}

	function calculateCost() {
		var total = 0;
		$(".output:visible").each(function() {
			var qty = $(this).val();
			var _index = $(this).attr('data-key');
			var cost = $("#priceUSD" + _index).val();
			total += qty * cost;
		});
		$(".total").text('$' + numeral(total).format('0,0.00'));
	}

	//Enable continue button over cart section
	function enableContinue() {
		if ($("table tr:visible").length == 1) {
			$('.btn-primary').unbind("click");
			$('.btn-primary').toggleClass('btn-secondary btn-primary');
		} else if ($('.btn-primary').length == 0) {
			$('.btn-secondary').toggleClass('btn-secondary btn-primary');
			$('.btn-primary').click(function() {
				$('#loader').modal('show');
				$("#purchaseOrder").submit();
				return true;
			});
		}
	}
</script>
<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->