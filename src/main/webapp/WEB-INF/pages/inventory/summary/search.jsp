<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
  	
<!-- -------------------------------------------------------------- --> 
<!-- Search product section -->
<!-- -------------------------------------------------------------- -->
          
	 	<div class="title-catalog">
			<b>SEARCH PRODUCT</b>
			<hr>			
		</div>	
		
		<div>
	        <h6 class="titulos mt-1">
	        	<label>SELECT PRODUCT</label>
	        </h6>
	    	<input type="text" class="ui-autocomplete-input form-control" data-beanName="product" />
		</div>

	     <div class="buttons text-right">	
	        <button type="button" class="btn btn-secondary btn-sm">
	        	&nbsp;Continue
	        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
	        </button>	        
	     </div>      

		<script>
			//Enable continue button over provider section
			function overwriteSelectAutocomplete(key, value){
				alert('key ' + key);
				$('.btn-secondary').removeClass('btn-secondary').addClass('btn-primary');
				$('.btn-primary').unbind( "click" );
				$('.btn-primary').click(function() {
					window.location.href = context()+"/inventory/summary/info/"+key;
				});						
			}	
		</script>

<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->