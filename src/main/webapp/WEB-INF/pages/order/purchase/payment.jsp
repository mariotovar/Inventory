<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="catalog order" method="POST" modelAttribute="purchaseOrder">

	<div id="payments" class="section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.payment.title"/> 
				&nbsp;-&nbsp; 
				<spring:message code="label.purchaseord.title"/> 
				&nbsp;${purchaseOrder.orderNumber}
			</b>
			<hr>			
		</div>	
	          
	     <div class="detail">
	         
	         <div class="row">
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.paid"/>&nbsp;USD
	         		</b>
	         	</h6>
	          </div>	          
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.toPay"/>&nbsp;USD 
	         		</b>
	         	</h6>
	          </div>
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.total"/>&nbsp;USD 
	         		</b>
	         	</h6>
	          </div>	              	              
	         </div>         
	         <div class="row">
		        <div class="col-12 col-md-4 text-right">
					<h4><span id="amountPaid"></span></h4>					
		        </div>
		        <div class="col-12 col-md-4 text-right">
		          <h4><span id="amountToPay"></span></h4>
		        </div>
		        <div class="col-12 col-md-4 text-right">
		        	<h4><span id="totalAmount"></span></h4>
		        	<input id="total" type="hidden" value="${purchaseOrder.totalUSD}"/>
		        </div>              	              
	         </div>     
	         	         		    	         
		    <div class="table-responsive table-reduce">
		    	<div class="text-right">
				     <button type="button" class="btn btn-outline-prin m-1 btn-add">
				        <i class="fas fa-plus"></i>
				     </button>	 		    		    	
		    	</div>
		         <table class="table table-sm text-center table-striped table-hover edit">
		             <tr class="primary">
		                 <th class="text-center">
		                 	<spring:message code="label.payment.date"/> 
		                 </th>
		                 <th class="text-center">
		                 	<spring:message code="label.payment.amountUSD"/> 
		                 </th>
		                 <th width="50%" class="text-center">
		                 	<spring:message code="label.payment.notes"/> 
		                 </th>
		             </tr>    	             		               
		        </table>     
		    </div>	 
		    
		     <div class="buttons text-right">
				<a href="${pageContext.request.contextPath}/view/lst/purchaseOrder" class="btn btn-info btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				<spring:message code="label.back"/> 	
		      	</a>			     
		        <button type="button" class="btn btn-success btn-sm">
		        	&nbsp;<spring:message code="label.payment.save"/> 
		        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
		        </button>
		     </div> 		            	         
	              
	     </div>

	</div>

	<!-- -------------------------------------------------------------- -->
	<!-- -------------------------------------------------------------- -->
	<script>

		var index = -1;
		$(document).ready(function(){
			
			enableContinue();
			
			$.ajax({
	    		async: true,
				type : "GET",
				url : context()+"/order/purchase/payments",
				dataType : 'json',
				success : function(response) {	
					$.each(response, function(index) {
						addPaymentRow(response[index]);
					});									
					calculateAmounts();					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("xhr " + xhr);
					alert("ajaxOptions " + ajaxOptions);
					alert("thrownError " + thrownError);
				}				
	    	});					
			$(".btn-add").click(function() {				
				addPaymentRow({
								pk: 0,	
								amountUSD: 0, 
								notes: '', 
								paymentDate: moment()
							});
			});
			
		});	
	
				
		function addPaymentRow(response){
			
			index++;
			var row = '';		
			$('tr:hidden').remove();							
			
			column = '';
			column += "<td class='text-left'>";
			column += "<button id='delete"+index+"' type='button' class='btn btn-sm btn-delete' data-key='"+index+"'>";
			column += "<span><i class='fas fa-times'></i></span>";
			column += "</button>";
			column += "<input name='payments["+index+"].year' type='hidden' value='"+${purchaseOrder.year}+"' />";
			column += "<input name='payments["+index+"].paymentDate' type='hidden' value='"+new Date(response.paymentDate)+"' />";
			column += "<label>"+moment(response.paymentDate).format("DD-MM-YYYY HH:mm")+"</label>";
			column += "</td>";
			row += column;
			
			column = '';
			column += "<td class='text-center'>";
			column += "<input id='qty"+index+"' name='payments["+index+"].amountUSD' class='form-control qty' type='text' size='4' maxlength='10' value='"+response.amountUSD+"' />";
			column += "</td>";
			row += column;
			
			column = '';
			column += "<td class='text-left'>";
			column += "<input name='payments["+index+"].notes' class='form-control notes' type='text' maxlength='100' value='"+response.notes+"' />";
			column += "</td>";
			row += column;			
			
			row='<tr>'+row+'</tr>';
			$('table> tbody:last').append(row);
									
			//Add delete event
			$("#delete"+index).click(function() {
				var _index = $(this).attr('data-key');
				$("#qty"+_index).val('0');
				$(this).closest('tr').hide();
				calculateAmounts();
				enableContinue();
			});	

			//Add onblur qty event
			$("#qty"+index).blur(function() {
				checkNumber($(this));
				calculateAmounts();
				//calculateTotal();
			});
		
			//Add row
			//calculateTotal();
			enableContinue();
				
		}
		

		function calculateAmounts(){
			var amountPaid = 0;
			$( ".qty" ).each(function() {
				var paid = $(this).val();
				amountPaid += parseFloat(paid);
			});	
			var totalAmount = parseFloat($('#total').val());
			var amountToPay = parseFloat(totalAmount - amountPaid);
			$( "#amountPaid" ).text(formatCurrency(amountPaid));	
			$( "#amountToPay" ).text(formatCurrency(amountToPay));	
			$( "#totalAmount" ).text(formatCurrency(totalAmount));	
		}
		
		//Enable continue button over cart section
		function enableContinue(){
			if($("table tr:visible").length == 1){
				$('.btn-success').unbind( "click" );
				$('.btn-success').toggleClass('btn-secondary btn-success');
			}
			else if ($('.btn-success').length==0){
				$('.btn-secondary').toggleClass('btn-secondary btn-success');
				$('.btn-success').click(function() {
					$('#loader').modal('show');
					$("#purchaseOrder").submit();
					return true;
				});					
			}			
		}
		
	
	</script>	
	<!-- -------------------------------------------------------------- -->
	<!-- -------------------------------------------------------------- -->

     
 </form:form> 