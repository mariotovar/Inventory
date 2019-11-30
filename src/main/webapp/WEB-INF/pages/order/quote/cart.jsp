<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	

  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Entity section -->
 	<!-- -------------------------------------------------------------- -->

    <div id="cart" class="wide-section">
    
 	   <form:form class="order" method="POST" modelAttribute="quoteOrder">
    
		 	<div class="title-catalog">				
				<b>
					<spring:message code="label.quote.title"/>
				</b>				
				<hr>			
			</div>	
			
			<br />
			
			<p class="title-catalog">
				<b><span class="total">$0.00</span>&nbsp;MXN</b>
			</p> 
		    
	        <h6 class="titulos mt-1">
	        	<form:label path="items">
	        		<spring:message code="label.cart.select"/>
	        	</form:label>
	        </h6>		    
		    <input type="text" class="ui-autocomplete-input form-control" data-beanName="product" />
	    	<c:forEach items="${conditions}" var="condition">			    	
	    		<input type="hidden" value="${condition.key}" data-pk="${condition}" class="condition">
	    	</c:forEach>		    
		    <div class="table-responsive table-items">
		         <table class="table table-sm text-center table-striped table-hover edit">
		         	<thead>
			             <tr class="primary">
			                 <th class="text-left">
			                 	<spring:message code="label.product.pnumber"/>
			                 </th>
			                 <th class="text-left">
			                 	<spring:message code="label.product.pdescription"/>
			                 </th>
			                 <th class="text-left">
			                 	<spring:message code="label.product.condition"/>
			                 </th>		                 
			                 <th class="text-center">
			                 	<spring:message code="label.cart.qty"/>
			                 </th>
			                 <th class="text-right">
			                 	<spring:message code="label.cart.unit.mxn"/>
			                 </th>
			                 <th class="text-right">
			                 	<spring:message code="label.cart.total.mxn"/>
			                 </th>
			             </tr> 
		             </thead>  
		            <tbody id="task-row">    
		            </tbody>		              	             		               
		        </table>     
		    </div>  
		    
		     <div class="buttons text-right">
				<a href="${pageContext.request.contextPath}/order/quote/client" class="btn btn-info btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
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
		$(document).ready(function(){			
			$.ajax({
	    		async: true,
				type : "GET",
				url : context()+"/order/quote/items",
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
		function overwriteSelectAutocomplete(key, value){			
			$.ajax({
				//cache:true,
	    		async: true,
				type : "GET",
				url : "../../search/find/product/pk/"+key,
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
		function addCartRow(catalog){
			
			key = catalog.pk;
			$('tr:hidden').remove();

			//Add plus qyt for duplicate key
			var duplicate = false;
			$(".qty").each(function() {
				if($(this).attr('data-key')==key){
					duplicate = true;
					$(this).val(parseInt($(this).val())+1);
					calculateSubTotal($(this));
				}
			});							
			
			if(!duplicate){
				
				index++;
				var row = '';			
				var cost = catalog.costMXN;
				var qty = (catalog.qty==undefined)?1:catalog.qty;
				var condition = (catalog.condition==undefined)?'':catalog.condition;
				
				column = '';
				column += "<td class='text-left'>";
				column += "<button id='delete"+key+"' type='button' class='btn btn-sm btn-delete' data-key='"+key+"'>";
				column += "<span><i class='fas fa-times'></i></span>";
				column += "</button>";
				column += "<input name='items["+index+"].pk' type='hidden' value='"+catalog.pk+"' />";
				column += "<input name='items["+index+"].value' type='hidden' value='"+catalog.value+"' />";
				column += "<label>"+catalog.value+"</label>";
				column += "</td>";
				row += column;
				
				column = '';
				column += "<td class='text-left'>";
				column += "<input name='items["+index+"].description' type='hidden' value='"+catalog.description+"' />";
				column += "<label>"+catalog.description+"</label>";
				column += "</td>";
				row += column;
				
	 			column = '';
				column += "<td>";
				column += "<select id='condition"+index+"' name='items["+index+"].condition' class='form-control' data-pk='"+catalog.condition+"'>";
				$(".condition").each(function() {
					var pk = $(this).attr('data-pk');
					column += "<option value='"+pk+"'>"+$(this).val()+"</option>";
				});			
				column += "</select>";
				column += "</td>";
				row += column;					
				
				column = '';
				column += "<td class='text-center'>";
				column += "<input id='qty"+key+"' name='items["+index+"].qty' class='qty form-control output' type='text' size='4' maxlength='6' value='"+qty+"' data-key='"+key+"' data-price='"+cost+"' />";
				column += "</td>";
				row += column;
				
				column = '';
				column += "<td class='text-right'>";
				column += "<input id='cost"+key+"' name='items["+index+"].costMXN' type='text' value='"+cost+"' class='qty form-control' data-key='"+key+"' />";
				//column += "<label>"+formatCurrency(cost)+"</label>";
				column += "</td>";
				row += column;					
								
				column = '';
				column += "<td class='text-right'>";
				column += "<label>";
				column += "<span id='subtotal"+key+"'>";
				column += formatCurrency(cost * qty);
				column += "</span>";
				column += "</label>";
				column += "</td>";
				row += column;					
				
				row='<tr>'+row+'</tr>';
				$('table> tbody:last').append(row);
										
				//Add delete event
				$("#delete"+key).click(function() {
					var _key = $(this).attr('data-key');
					$("#qty"+_key).val('0');
					$(this).closest('tr').hide();
					calculateCost();
					enableContinue();
				});	
				
				//Add onblur qty event
				$("#qty"+key).blur(function() {
					checkNumber($(this));
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
			
			var condition = $('#condition'+index).attr('data-pk');
			$('#condition'+index+' option[value='+condition+']').attr('selected', 'selected');
			
			//Add row
			calculateTotal();
			calculateCost();
				
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
		function enableContinue(){
			if($("table tr:visible").length == 1){
				$('.btn-primary').unbind( "click" );
				$('.btn-primary').toggleClass('btn-secondary btn-primary');
			}
			else if ($('.btn-primary').length==0){
				$('.btn-secondary').toggleClass('btn-secondary btn-primary');
				$('.btn-primary').click(function() {
					$('#loader').modal('show');
					$("#quoteOrder").submit();
					return true;
				});					
			}			
		}
		
	
	</script>	
	<!-- -------------------------------------------------------------- -->
	<!-- -------------------------------------------------------------- -->