<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Entity section -->
 	<!-- -------------------------------------------------------------- -->

    <div id="cart" class="section">

		<p><span class="total">0.00</span>&nbsp;USD</p><hr> 
	    
	    <label>Select product:</label>
	    <input type="text" class="ui-autocomplete-input form-control" data-beanName="product" />
	    
	    <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover edit">
	             <tr class="primary">
	                 <th class="text-left">Part Number</th>
	                 <th class="text-left">Part Description</th>
	                 <th class="text-center">Qty.</th>
	                 <th class="text-right">Unit USD</th>
	                 <th class="text-right">Total USD</th>
	             </tr>      
	        </table>     
	    </div>  
	    
	     <div class="buttons text-right">
	        <button type="button" class="btn btn-secondary btn-sm">
	        	&nbsp;Continue
	        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
	        </button>
	     </div> 	    
	        	          
	</div>
	
	
	<script>
		
		//Add row on select by key
		function overwriteSelectAutocomplete(key, value){							
			$.ajax({
				//cache:true,
	    		async: true,
				type : "GET",
				url : "../../search/find/product/pk/"+key,
				dataType : 'json',
				success : function(response) {
					addCartRow(response);					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("error");
				}				
	    	});	
		}
		
		//Add row on enter by value
		<!--
		$('.ui-autocomplete-input').onEnter(function() {			
			$.ajax({
	    		async: true,
				type : "GET",
				url : "../../search/find/product/value/"+$(this).val(),
				dataType : 'json',
				success : function(response) {
					if(response.status=='SUCCESS'){
						addCartRow(response);	
					}										
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("error");
				}				
	    	});	
		});	
		-->
		
		//Add row to cart
		function addCartRow(response){
			
			key = response.catalog.pk;

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

				var row = '';			
				var price = response.catalog.priceUSD;
				
				column = '';
				column += "<td class='text-left'>";
				column += "<button id='delete"+key+"' type='button' class='btn btn-sm btn-delete'>";
				column += "<span><i class='fas fa-times'></i></span>";
				column += "</button>";
				column += response.catalog.uniqueValue;
				column += "</td>";
				row += column;
				
				column = '';
				column += "<td class='text-left'>";
				column += response.catalog.partDescription;
				column += "</td>";
				row += column;
				
				column = '';
				column += "<td class='text-center'>";
				column += "<input id='qty"+key+"' class='qty' type='text' size='4' maxlength='6' value='1' data-key='"+key+"' data-price='"+price+"' />";
				column += "</td>";
				row += column;
				
				column = '';
				column += "<td class='text-right'>";
				column += formatCurrency(price);
				column += "</td>";
				row += column;					
				
				column = '';
				column += "<td class='text-right'>";
				column += "<span id='subtotal"+key+"'>";
				column += formatCurrency(price);
				column += "</span>";
				column += "</td>";
				row += column;					

				row='<tr>'+row+'</tr>';
				$('table> tbody:last').append(row);
										
				//Add delete event
				$("#delete"+key).click(function() {								
					$(this).closest('tr').remove();
					calculateTotal();
					enableContinue();
				});	
				
				//Add onblur qty event
				$("#qty"+key).blur(function() {
					checkNumber($(this));
					calculateSubTotal($(this));
					calculateTotal();
				});	
										
			}
			
			//Add row
			calculateTotal();
			enableContinue();
				
		}
		
		
		//Enable continue button over cart section
		function enableContinue(){
			if($("table tr").length == 1){
				$('.btn-primary').unbind( "click" );
				$('.btn-primary').toggleClass('btn-secondary btn-primary');
			}
			else if ($('.btn-primary').length==0){
				$('.btn-secondary').toggleClass('btn-secondary btn-primary');
				$('.btn-primary').click(function() {
					alert('go to the next section');
				});					
			}			
		}
		
	
	</script>	
	<!-- -------------------------------------------------------------- -->
	<!-- -------------------------------------------------------------- -->