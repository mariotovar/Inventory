<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="catalog order" method="POST" modelAttribute="purchaseOrder">

	<div id="expenses" class="wide-section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.expense.title"/> 
				&nbsp;-&nbsp; 
				<spring:message code="label.purchaseord.title"/> 
				&nbsp;${purchaseOrder.orderNumber}
			</b>
			<hr>			
		</div>	
		
		<br />
		<p class="info-total"><span class="total">$0.00</span>&nbsp;MXN</p> 
		    			          
	     <div class="detail">
  		    	         
		    <div class="table-responsive table-reduce">
		    	<div class="text-right">
				     <button type="button" class="btn btn-outline-prin m-1 btn-add">
				        <i class="fas fa-plus"></i>
				     </button>	 		    		    	
		    	</div>
		    	<c:forEach items="${expenseTypes}" var="expenseType">
       				<c:set var="label_type">
						<spring:message code="label.expense.type.${expenseType}"/>
                 	</c:set>			    	
		    		<input type="hidden" value="${label_type}" data-pk="${expenseType.pk}" class="expenseType">
		    	</c:forEach>
		        <table class="table table-sm text-center table-striped table-hover edit">
		             <tr class="primary">
		                 <th class="text-center">
		                 	<spring:message code="label.expense.date"/> 
		                 </th>
		                 <th class="text-center">
		                	<spring:message code="label.expense.type"/> 
		                </th>
		                 <th class="text-center">
		                 	<spring:message code="label.expense.amount"/>&nbsp;MXN 
		                 </th>
		                 <th width="50%" class="text-center">
		                 	<spring:message code="label.expense.notes"/> 
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
		        	&nbsp;<spring:message code="label.expense.save"/> 
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
				url : context()+"/order/purchase/expenses",
				dataType : 'json',
				success : function(response) {					
					$.each(response, function(index) {
						addExpenseRow(response[index]);
					});										
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("xhr " + xhr);
					alert("ajaxOptions " + ajaxOptions);
					alert("thrownError " + thrownError);
				}				
	    	});					
			$(".btn-add").click(function() {				
				addExpenseRow({
								type: 1,
								amountMXN: 0, 
								notes: '', 
								expenseDate: moment()
							});
			});
			
		});	
	
				
		function addExpenseRow(response){
			
			index++;
			var row = '';
			$('tr:hidden').remove();			
			
			column = '';
			column += "<td class='text-left'>";
			column += "<button id='delete"+index+"' type='button' class='btn btn-sm btn-delete' data-key='"+index+"'>";
			column += "<span><i class='fas fa-times'></i></span>";
			column += "</button>";
			column += "<input name='expenses["+index+"].expenseDate' type='hidden' value='"+new Date(response.expenseDate)+"' />";
			column += "<label>"+moment(response.expenseDate).format("DD-MM-YYYY HH:mm")+"</label>";
			column += "</td>";
			row += column;
			
 			column = '';
			column += "<td>";
			column += "<select id='type"+index+"' name='expenses["+index+"].type' class='form-control' data-pk='"+response.type+"'>";
			$(".expenseType").each(function() {
				var pk = $(this).attr('data-pk');
				column += "<option value='"+pk+"'>"+$(this).val()+"</option>";
			});			
			column += "</select>";
			column += "</td>";
			row += column;			
			
			column = '';
			column += "<td class='text-center'>";
			column += "<input id='qty"+index+"' name='expenses["+index+"].amountMXN' class='form-control qty' type='text' size='4' maxlength='10' value='"+response.amountMXN+"' />";
			column += "</td>";
			row += column;
			
			column = '';
			column += "<td class='text-left'>";
			column += "<input name='expenses["+index+"].notes' class='form-control notes' type='text' maxlength='100' value='"+response.notes+"' />";
			column += "</td>";
			row += column;			
			
			row='<tr>'+row+'</tr>';
			$('table> tbody:last').append(row);
									
			//Add delete event
			$("#delete"+index).click(function() {
				var _index = $(this).attr('data-key');
				$("#qty"+_index).val('0');
				$(this).closest('tr').hide();
				calculateTotal();
				enableContinue();
			});	

			//Add onblur qty event
			$("#qty"+index).blur(function() {
				checkNumber($(this));
				calculateTotal();
			});
		
			var type = $('#type'+index).attr('data-pk');
			$('#type'+index+' option[value='+type+']').attr('selected', 'selected');
			
			calculateTotal();
			enableContinue();
				
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