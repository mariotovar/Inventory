(function($) {
    $.fn.onEnter = function(func) {
        this.bind('keypress', function(e) {
            if (e.keyCode == 13) func.apply(this, [e]);    
        });               
        return this; 
     };
})(jQuery);

$(function(){
    $(document).on('click','input[type=text]',function(){
    	this.select();
    });
    $( ".qty" ).blur(function() {
    	input =  $(this);
    	input.val($.isNumeric(input.val()) && parseFloat(input.val())>0?input.val():0);
    });	
    $( ".qtyFactor" ).blur(function() {
    	input =  $(this);
    	input.val($.isNumeric(input.val()) && parseInt(input.val())>0?input.val():1);
    });	
    $( ".back" ).click(function() {
    	window.history.back();
    });	    
	$(".currency").each(function() {
		$(this).text(formatCurrency($(this).text()));
	});	
	$(".amount").each(function() {
    	input =  $(this);
		input.val(parseFloat(input.val()).toFixed(2));
	});		
});

function formatCurrency(doubleValue){
	var formatValue = "$" + numeral(doubleValue).format('0,0.00');
	return formatValue;
}

function formatAmount(doubleValue){
	var formatValue = numeral(doubleValue).format('##0.00');
	return formatValue;
}	
				
function checkNumber(input){
	input.val($.isNumeric(input.val())?input.val():0);
}

function calculateSubTotal(input){	
	var qty = input.val();
	var key = input.attr('data-key');
	var price = input.attr('data-price');
	$("#subtotal"+key).text(formatCurrency(qty*price));	
}

function calculateTotal(){
	var total = 0;
	$( ".qty" ).each(function() {
		var price = 1;
		var qty = $(this).val();
		if($(this).attr('data-price') != undefined){
			price = $(this).attr('data-price');	
		}
		total += qty * price;
	});
	$( ".total" ).text('$' + numeral(total).format('0,0.00'));	
}



