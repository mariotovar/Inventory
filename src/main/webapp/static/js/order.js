$(document).ready(function(){
		

		$(".step").hide();		

		/* ************************************************************ */
		/* Search list provider by name */
		/* ************************************************************ */
		$( "#input-provider" ).onEnter(function() {

			var input = $(this);
			var key = input.attr('data-key');
			
			$.ajax({
	    		async: true,
				type : "GET",
				url : "../../search/find/provider/"+key,
				dataType : 'json',
				success : function(response) {
					$('.step').show();
					input.closest('.secciones').hide();	
					$('#provider-name').val(response.catalog.name);
					$('#provider-email').val(response.catalog.email);
					$('#provider-mobile').val(response.catalog.mobilePhone);					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					input.prev().show();
				}				
	    	});	
									
		});
		/* ************************************************************ */
		

		/* ************************************************************ */
		/* Search list products by name */
		/* ************************************************************ */
		$( "#input-product" ).onEnter(function() {
			
			var input = $(this);
			var key = input.attr('data-key');
			
			$.ajax({
	    		async: true,
				type : "GET",
				url : "../../search/find/product/"+key,
				dataType : 'json',
				success : function(response) {
					
					var $row = $(
								'<tr id='+key+'>'+
							      '<td class="text-left">'+
				                 		'<button type="button" class="btn btn-sm btn-delete">'+
		                 					'<span><i class="fas fa-times"></i></span>'+
		                 				'</button>'+							      
							      		response.catalog.partNumber+
							      '</td>'+
							      '<td class="text-left">'+response.catalog.partDescription+'</td>'+
							      '<td>'+
							      	'<input class="qty" id="productQty'+key+'.qty" name="productQty['+key+'].qty" type="input" size="6" value="1" maxlength="6" />'+
							      '</td>'+
							      '<td class="text-right">'+
							      	'<span>'+formatCurrency(response.catalog.priceUSD)+'<span>'+
							      '</td>'+
							      '<td class="text-right">'+
							      	'<input id="parcial'+key+'" type="hidden" value="'+response.catalog.priceUSD+'" />'+
							      	'<span>'+formatCurrency(response.catalog.priceUSD)+'<span>'+
							      '</td>'+
//							      '<td>'+
//							      	'<input id="productRows0.notes" name="productRows[0].notes" type="input" size="20" value="1"/>'+
//							      '</td>'+							      							      
							     '</tr>'
						      );    								
					$('table> tbody:last').append($row);
					$('tr[id='+key+'] .btn-delete').click(function() {		
						$(this).closest('tr').remove();	
						calculateTotal();
					});														
					$('tr[id='+key+'] .qty').blur(function() {	
						
						inputNumberValue($(this));	
						var value = parseFloat($(this).val());
						var price = parseFloat(response.catalog.priceUSD);	
						var selectedKey = $(this).closest('tr').attr("id");	
						var parcial = parseFloat(price * value);
						$('#parcial'+selectedKey).val(parcial);
						$('#parcial'+selectedKey).next().text(formatCurrency(parcial));
						calculateTotal();						
					});						
					calculateTotal();						
				},
				error : function(xhr, ajaxOptions, thrownError) {
					input.prev().show();
				}				
	    	});		
			
		});		
				
		function calculateTotal(){		
			var total = 0;
			$('#total').val(formatCurrency(0));
			$( "input[id^=parcial]" ).each(function() {
				var parcial = $(this).val();
				total += parseFloat(parcial);
				$('#total').val(formatCurrency(total));
			});	
		}
			
		function inputNumberValue(input){
			input.val($.isNumeric(input.val())?input.val():0);
		}
		
		function formatCurrency(doubleValue){
			var formatValue = '$' + parseFloat(doubleValue, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString();
			return formatValue;
		}
		
		
		
	}
);


(function($) {
    $.fn.onEnter = function(func) {
        this.bind('keypress', function(e) {
            if (e.keyCode == 13) func.apply(this, [e]);    
        });               
        return this; 
     };
})(jQuery);