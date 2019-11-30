<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="catalog order" method="POST" modelAttribute="purchaseOrder">

 	<div class="label-success">
		* The operation was successful.
	</div>

 	<!-- -------------------------------------------------------------- --> 
 	<!-- Order purchase information -->
 	<!-- -------------------------------------------------------------- -->
 	
    <div class="secciones">
     
         <div class="row">
          <div class="col-12 col-md-4">
            <h6 class="titulos mt-1">
         		<form:label path="orderNumber">Order Number</form:label>
         	</h6>
          </div>
          <div class="col-12 col-md-4">
            <h6 class="titulos mt-1">
         		<form:label path="purchaseDate">Purchase Date</form:label>
         	</h6>
          </div>
          <div class="col-12 col-md-2">
            <h6 class="titulos mt-1">
         		Total Price USD
         	</h6>
          </div>
          <div class="col-12 col-md-2 text-right">
          </div>	              	              
         </div>         
         <div class="row">
          <div class="col-12 col-md-4">
             <form:input path="orderNumber" type="text" class="form-control" readonly="true" />
          </div>
          <div class="col-12 col-md-4">
			<fmt:formatDate value="${purchaseOrder.purchaseDate}" var="dateString" pattern="dd-MM-yyyy HH:mm" />          
            <form:input path="purchaseDate" type="text" class="form-control" readonly="true" value="${dateString}" />
          </div>
          <div class="col-12 col-md-4">
            <input id="total" type="text" class="form-control text-right" readonly="readonly" value="0.00">
          </div>              	              
         </div>
     </div> 

 	<!-- -------------------------------------------------------------- -->
 
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Provider information -->
 	<!-- -------------------------------------------------------------- -->
     
     <div id="search-provider" class="secciones row">
        <div class="col-12 col-md-12 mt-2">
	  		<span class="error-input">Not found provider. Please check your enter!!!</span>
	  		<input id="input-provider" type="text" class="form-control inputSearch" placeholder="Search Provider" data-beanName="provider">
			<ul class="list-search"></ul>
        </div>  
        <!--       
        <div class="col-12 col-md-1 mt-2 text-left">
	      <button id="find-provider"  type="button" class="btn btn-outline-prin m-1">
	        <i class="fas fa-plus"></i>
	      </button>	     
       </div>
       -->
    </div>     
          
     <div id="provider" class="step secciones">
         <div class="row">
          <div class="col-12 col-md-4">
            <h6 class="titulos mt-1">
         		Provider
         	</h6>
          </div>
          <div class="col-12 col-md-4">
            <h6 class="titulos mt-1">
         		Email
         	</h6>
          </div>
          <div class="col-12 col-md-2">
            <h6 class="titulos mt-1">
         		Mobile phone
         	</h6>
          </div>
          <div class="col-12 col-md-2 text-right">
          </div>	              	              
         </div>         
         <div class="row">
	        <div class="col-12 col-md-4">
	          <form:input path="pkProvider" type="hidden" class="form-control" />
	          <input id="provider-name" type="text" class="form-control" readonly="readonly">
	        </div>
	        <div class="col-12 col-md-4">
	          <input id="provider-email" type="text" class="form-control" readonly="readonly">
	        </div>
	        <div class="col-12 col-md-4">
	          <input id="provider-mobile" type="text" class="form-control" readonly="readonly">
	        </div>              	              
         </div>     
         <hr>         
     </div>

 	<!-- -------------------------------------------------------------- -->
 	
 	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Product information -->
 	<!-- -------------------------------------------------------------- --> 	
 	
     <div id="search-product" class="secciones row step">
        <div class="col-12 col-md-12 mt-2">
        	<span class="error-input">Not found product. Please check your enter!!!</span>
	  		<input id="input-product" type="text" class="form-control inputSearch" placeholder="Search Product" data-beanName="product">
			<ul class="listSearch"></ul>
        </div>       
        <!--  
        <div class="col-12 col-md-1 mt-2 text-left">
	      <button id="add-product"  type="button" class="btn btn-outline-prin m-1">
	        <i class="fas fa-plus"></i>
	      </button>	
       </div>
        -->
    </div>   
         
	<div id="products" class="step secciones">
	    
	     <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover edit">
	             <tr class="primary">
	                 <th class="text-left">Part Number</th>
	                 <th class="text-left">Part Description</th>
	                 <th>Qty.</th>
	                 <th class="text-right">Unit USD</th>
	                 <th class="text-right">Total USD</th>
	             </tr>      
	        </table>     
	     </div>  
	        	          
     </div>
     
 	<!-- -------------------------------------------------------------- -->     
     
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Product information -->
 	<!-- -------------------------------------------------------------- --> 	   
     
     <div class="step secciones actions">       
          <div class="text-right">
        	<input type="checkbox" checked="checked"> Enviar correo al proveedor  	  
          </div>     
          <div class="buttons text-right">
                <button type="button" class="btn btn-secondary btn-sm back">
                	<span><i class="fas fa-times"></i></span> 
                	&nbsp;Cancelar la Orden de Compra
                </button>
                <button type="button" class="btn btn-success btn-sm submit">
                	<span><i class="fas fa-save"></i></span> 
                	&nbsp;Generar Nueva de Orden de Compra
                </button>
          </div>            
     </div>
     <div class="results">           
          <div class="buttons text-right">
                <a href="${pageContext.request.contextPath}/view/detail/purchaseOrder"
                   type="button" class="btn btn-primary btn-sm">
                	<span><i class="fas fa-file-alt"></i></span> 
                	&nbsp;Ver Detalle de la Orden de Compra
                </a>
          </div>            
     </div>     
     
     <!-- -------------------------------------------------------------- --> 
     
 </form:form> 