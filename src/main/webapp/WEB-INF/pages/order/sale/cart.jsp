<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!-- -------------------------------------------------------------- -->
<!-- Cart section -->
<!-- -------------------------------------------------------------- -->


<div id="cart" class="wide-section">

	<form:form class="order" method="POST" modelAttribute="saleOrder">

		<div class="title-catalog">
			<b>
				<spring:message code="label.saleord.title"/>
			</b>
			<hr>
		</div>

		<br />

		<p class="info-total">
			<span class="total">$0.00</span>&nbsp;MXN
		</p>

		<h6 class="titulos mt-1">
			<form:label path="items">
				<spring:message code="label.cart.select"/>
			</form:label>
		</h6>
		<input type="text" class="ui-autocomplete-input form-control"
			data-beanName="product" />

		<span id="items"></span>
		<div class="table-responsive table-items">
			<table
				class="table table-sm text-center table-striped table-hover edit">
				<tr class="primary">
					<th class="text-left">
						# <spring:message code="label.cart.lot"/>
					</th>
	                 <th class="text-left">
	                 	<spring:message code="label.product.pnumber"/>
	                 </th>
	                 <th class="text-left">
	                 	<spring:message code="label.product.pdescription"/>
	                 </th>
	                 <th class="text-left">
	                 	<spring:message code="label.product.condition"/>
	                 </th>	                 
					<th class="text-right">
						<spring:message code="label.cart.stock"/>
					</th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.qty"/>
	                 </th>
	                 <th width="15%" class="text-right">
	                 	<spring:message code="label.cart.unit.mxn"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.total.mxn"/>
	                 </th>
				</tr>
			</table>							
		</div>
	
		<div class="buttons text-right">
			<a href="${pageContext.request.contextPath}/order/sale/client"
				class="btn btn-info btn-sm"> <span><i
					class="fas fa-times"></i></span>
					<spring:message code="label.back"/>
			</a>
			<button type="button" class="btn btn-secondary btn-sm">
				&nbsp;<spring:message code="label.continue"/>
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
			url : context() + "/order/sale/items",
			dataType : 'json',
			success : function(response) {
				$.each(response, function(index) {
					addCartRow(response[index]);
				});
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("xhr " + xhr);
				alert("ajaxOptions " + ajaxOptions);
				alert("thrownError " + thrownError);
			}
		});

	});

	//Add row on select by key
	function overwriteSelectAutocomplete(key, value) {
		$.ajax({
			async : true,
			type : "GET",
			url : context() + "/order/sale/lots/" + key,
			dataType : 'json',
			success : function(item) {
				if(item != null){
					addCartRow(item);	
				}				
			}
		});
	}

	//Add row to cart
	function addCartRow(item) {

		index++;
		$('tr:hidden').remove();

		for (var i = 0; i < item.inputs.length; i++) {

			var row = '';
			var input = item.inputs[i];
			var key = input.lot;
			
			if(!isDuplicateLot(key)){
			
				if(i==0){
					$('#items').append("<input name='items["+index+"].pk' type='hidden' value='"+item.pk+"' />");
					$('#items').append("<input name='items["+index+"].value' type='hidden' value='"+item.value+"' />");
					$('#items').append("<input name='items["+index+"].description' type='hidden' value='"+item.description+"' />");
					$('#items').append("<input name='items["+index+"].condition' type='hidden' value='"+item.condition+"' />");
				}
	
				column = '';
				column += "<td class='text-left'>";
				column += "<button id='delete"+key+"' type='button' class='btn btn-sm btn-delete' data-key='"+key+"'>";
				column += "<span><i class='fas fa-times'></i></span>";
				column += "</button>";
				column += "<input name='items["+index+"].inputs["+i+"].lot' type='hidden' value='"+input.lot+"' />";
				column += "<label>" + input.lotNumber + "</label>";
				column += "</td>";
				row += column;
	
				column = '';
				column += "<td class='text-left'>";
				column += "<label>" + item.value + "</label>";
				column += "</td>";
				row += column;
	
				column = '';
				column += "<td class='text-left'>";
				column += "<label>" + item.description + "</label>";
				column += "</td>";
				row += column;
				
				column = '';
				column += "<td class='text-left'>";
				column += "<label>" + item.keyCondition + "</label>";
				column += "</td>";
				row += column;				
	
				column = '';
				column += "<td class='text-right'>";
				column += "<label>" + input.stock + "</label>";
				column += "<input name='items["+index+"].inputs["+i+"].stock' type='hidden' value='"+input.stock+"' />";
				column += "</td>";
				row += column;
	
				column = '';
				column += "<td class='text-left'>";
				column += "<select id='qty"+key+"' name='items["+index+"].inputs["+i+"].outputs[0].qty' class='form-control text-right output' data-key='"+key+"'>";
				for (var j = 0; j < input.stock; j++) {
					if (input.outputs[0] != undefined && input.outputs[0].qty == (j + 1)) {
						column += "<option value='" + (j + 1)
								+ "' selected='selected'>" + (j + 1) + "</option>";
					} else {
						column += "<option value='" + (j + 1) + "'>" + (j + 1)
								+ "</option>";
					}
				}
				column += "</select>";
				column += "</td>";
				row += column;
	
				column = '';
				column += "<td class='text-right'>";
				if (input.outputs[0] != undefined && input.outputs[0].costMXN != 0) {
					column += "<input id='cost"+key+"' name='items["+index+"].inputs["+i+"].outputs[0].costMXN' type='text' value='"+input.outputs[0].costMXN+"' class='form-control text-right' data-key='"+key+"' />";
				} else {
					column += "<input id='cost"+key+"' name='items["+index+"].inputs["+i+"].outputs[0].costMXN' type='text' value='"+item.costMXN+"' class='form-control text-right' data-key='"+key+"' />";
				}
				column += "</td>";
				row += column;
	
				column = '';
				column += "<td class='text-right'>";
				column += "<label>";
				column += "<span id='subtotal"+key+"'>";
				column += formatCurrency(item.costMXN);
				column += "</span>";
				column += "</label>";
				column += "</td>";
				row += column;
	
				row = '<tr>' + row + '</tr>';
				$('table> tbody:last').append(row);
	
				//Add delete event
				$("#delete" + key).click(function() {
					var _key = $(this).attr('data-key');
					$("#cost"+_key).val('0');
					$(this).closest('tr').hide();
					enableContinue();
					calculateCost();
				});
	
				$("#qty" + key).change(function() {
					var _key = $(this).attr('data-key');
					calculateSubCost(_key);
					calculateCost();
				});
	
				$("#cost" + key).blur(function() {
					checkNumber($(this))
					var _key = $(this).attr('data-key');
					calculateSubCost(_key);
					calculateCost();
				});
				
				calculateSubCost(key);

			}
			
		}

		calculateCost();

	}	

	function isDuplicateLot(lot) {
		var isDuplicate = false;
		$( ".output:visible" ).each(function() {
			var _key = $(this).attr('data-key');
			if(lot == _key){
				isDuplicate = true;
			}
		});
		return isDuplicate;
	}

	function calculateSubCost(_key) {
		var subtotal = 0;
		var qty = $("#qty" + _key).val();
		var cost = $("#cost" + _key).val();
		$("#subtotal" + _key).text(formatCurrency(qty * cost));
	}
	
	function calculateCost() {
		var total = 0;
		$( ".output:visible" ).each(function() {
			var qty = $(this).val();
			var _key = $(this).attr('data-key');
			var cost = $("#cost" + _key).val();
			total += qty * cost;
		});
		if(total == 0){
			$('.btn-primary').unbind("click");
			$('.btn-primary').toggleClass('btn-secondary btn-primary');			
		}
		else{
			enableContinue();
		}
		$( ".total" ).text('$' + numeral(total).format('0,0.00'));	
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
				$("#saleOrder").submit();
				return true;
			});
		}
	}
	

	
</script>
<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->