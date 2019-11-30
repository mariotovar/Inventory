<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="catalog order" method="POST" modelAttribute="inventoryInput">

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
         		<form:label path="inventoryDate">Inventory Date</form:label>
         	</h6>
          </div>
          <div class="col-12 col-md-2">
            <h6 class="titulos mt-1">
         		Total Price
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
            <form:input path="inventoryDate" type="text" class="form-control" readonly="true" />
          </div>
          <div class="col-12 col-md-4">
            <input type="text" class="form-control text-right" readonly="readonly" value="0.00 USD">
          </div>              	              
         </div>
     </div> 

 	<!-- -------------------------------------------------------------- -->
          

     <div id="search-products" class="secciones">
     
   		<nav class="navbar navbar-light bg-light mt-3">            
		  <div class="input-group">
   			<input type="text" class="form-control inputSearch" placeholder="Search Product" data-beanName="product">
			<ul class="list-group listSearch"></ul>
		    <div class="input-group-btn">
		      <button id="find-product" type="button" class="btn btn-outline-prin m-1">
		        <i class="fas fa-plus"></i>
		      </button>
		    </div>
		  </div>	     
	    </nav>     
     
     </div>
     
    
	<div id="products" class="secciones">
	    
	     <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover">
	             <tr class="primary">
	                 <th>Part Number</th>
	                 <th>Part Description</th>
	                 <th>Qty.</th>
	                 <th>Unit USD</th>
	                 <th>Total USD</th>
	                 <th>Notes</th>
	                 <th>Delete</th>
	             </tr>  	                  
	        </table>     
	     </div>  
	     
	      <button type="button" class="btn btn-success btn-sm submit">
	      	<span><i class="fas fa-save"></i></span> 
	      	Guardar
	      </button>	     
	     
	     <div class="text-right">
	     	<button id="step2" type="button" class="btn btn-warning btn-next">Continuar</button>
	     </div>
	        	          
     </div>
     
   
     
     <div id="confirmation" class="step secciones">
       
          <div class="text-right">
                <button type="button" class="btn btn-secondary btn-sm"><span><i class="fas fa-times"></i></span> Regresar</button>
                <button type="button" class="btn btn-success btn-sm"><span><i class="fas fa-save"></i></span> Generar Entrada al Inventario</button>
          </div>              
    
     </div>
     
     
 </form:form> 