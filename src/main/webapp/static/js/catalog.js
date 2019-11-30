$(document).ready(
	function(){

		//Hide success label
		$( ".label-success" ).hide();	
		//Add error span
		$(".catalog").find("input, select").each(function() {
			$(this).after($("<span class='error' />"));				
		});

		if($( ".label-read" ).length > 0){
			//Disabled inputs for read catalog
			//$( "select" ).prop("disabled", true);
			$( "select" ).css('pointer-events','none');
			$( ".form-control" ).prop("readonly", true);	
		}
		
		$(".submit").click(function(e) {		
				
			e.preventDefault();
			var form = $(this).closest('form');			
			var beanName = form.attr("id");
			
			$.ajax({
	    		async: true,
				type : "POST",
				url : "../form/"+beanName,
				data : form.serialize(),
				dataType : 'json',
			    beforeSend: function(){
			    	$('#loader').modal('show');
				},				
				success : function(response) {
					//Clean errors
					form.find(".error").text("").html();
					if(response.status=="SUCCESS"){
						$( ".label-warning" ).hide();
						$( ".label-success" ).show();
						$( "select" ).prop("disabled", true);
						$( ".form-control" ).prop("readonly", true);		
						//change actions buttons for success button
						form.find(".actions").remove();
						form.find(".results").removeClass('results');
					}
					else if(response.status=="ERROR"){			
						//Print input errors
						form.find("input, select").each(function() {
							var key = $(this).attr("name");
							var error = response.errors[key];
							if(error!=undefined){
								var span = $(this).next("span");
								span.append("&nbsp;<i class='fas fa-exclamation-circle'></i>&nbsp;");
								span.append(error).html();	
							}								
						});
					}
					
				},
			    complete: function(){
					setTimeout(function() {
					      $("#loader").modal("hide");
					    }, 500);
				},				
				error : function(xhr, ajaxOptions, thrownError) {
					//go to error page
			        alert("submit "+ xhr.status);
			        alert("submit "+ thrownError);
				}				
	    	});							
		});

	
		/*
		$( ".ui-autocomplete-input" ).autocomplete({
			  create: function( event, ui ) {
					
					beanName = $(this).attr('data-beanName');
					
					$.ajax({
			    		async: true,
			    		cache: true,
						type : "GET",
						url : context()+"/search/create/"+beanName,
						dataType : 'json',
						beforeSend: function(response) {	
							$('#loader').modal('show');
						},
					    complete: function(){
							setTimeout(function() {
							      $("#loader").modal("hide");
							    }, 500);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							//go to error page
					        alert('autocomplete: ' + xhr.status);
					        alert('autocomplete: ' + thrownError);
						}				
			    	});					
					
					
			  }
		});
		*/		
		
		$('.ui-autocomplete-input').keyup(function(e) {
			input = $(this);
			inputValue = $(this).val();			
			beanName = $(this).attr('data-beanName');
			
			if(inputValue.length >= 3){		
				$.ajax({
		    		async: true,
		    		cache: true,
					type : "GET",
					url : context()+"/search/lst/"+beanName+"/"+inputValue,
					dataType : 'json',
					success : function(response) {											  	
					    var items = [];					     
						$.map( response.mapRows, function( value, key ) {	
							items.push({ key: key, value: value });								
						});					    					
						input.autocomplete({
							source: items,
							focus: function (event, ui) {
								return false;
							},
							select: function(event, ui){
								overwriteSelectAutocomplete(ui.item.key, ui.item.value);
							}
						});
					},
					error : function(xhr, ajaxOptions, thrownError) {
						//go to error page
				        alert('autocomplete: ' + xhr.status);
				        alert('autocomplete: ' + thrownError);
					}				
		    	});
			}
		});		
		
		$('.ui-autocomplete-input').onEnter(function() {					
			input = $(this);
			inputValue = $(this).val();			
			beanName = $(this).attr('data-beanName');
			
			$.ajax({
	    		async: true,
				type : "GET",
				url : context()+"/search/find/"+beanName+"/value/"+inputValue,
				dataType : 'json',
				success : function(response) {
					if(response.status=='SUCCESS'){						
						overwriteSelectAutocomplete(response.catalog.pk, response.catalog.value);
					}										
				},
				error : function(xhr, ajaxOptions, thrownError) {
			        alert('onEnter: ' + xhr.status);
			        alert('onEnter: ' + thrownError);
				}				
	    	});	
		});			
		
		$('.inputSelect option').remove();
		$(".inputSelect").each(function() {
			input = $(this);
			pk = $(this).attr("data-pk");
			beanName = $(this).attr('data-beanName');	
			$.ajax({
	    		async: true,
				type : "GET",
				url : context()+"/select/lst/"+beanName,
				dataType : 'json',
				success : function(response) {				
					input.append('<option value="0"> >> Select '+beanName+'.</option>');	
					$.map( response.mapRows, function( value, key ) {
						if(pk != key){
							input.append('<option value="'+key+'">'+value+'</option>');
						}
						else{
							input.append('<option value="'+key+'" selected="selected">'+value+'</option>');
						}
												        													
					});					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					//go to error page
			        alert("select " + xhr.status);
			        alert("select " + thrownError);
				}				
	    	});
		});		
		
		
		$(".map-submit").click(function(e) {		
			
			e.preventDefault();
			var form = $(this).closest('form');			
			
			$.ajax({
	    		async: true,
				type : "POST",
				url : window.location.pathname,
				data : form.serialize(),
				dataType : 'json',
				success : function(response) {
					if(response.status=="SUCCESS"){
						$( ".label-success" ).show();
						$( ".form-control" ).prop("readonly", true);		
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					//go to error page
			        alert("submit "+ xhr.status);
			        alert("submit "+ thrownError);
				}				
	    	});							
		});
				
	}
);